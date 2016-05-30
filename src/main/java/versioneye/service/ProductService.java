package versioneye.service;

import org.bson.types.ObjectId;
import versioneye.domain.*;
import versioneye.persistence.INewestDao;
import versioneye.persistence.INotificationDao;
import versioneye.persistence.IProductDao;
import versioneye.utils.LogUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 3:56 PM
 */
public class ProductService {

    protected IProductDao productDao;
    protected INotificationDao notificationDao;
    protected INewestDao newestDao;
    private LogUtils logUtils;

    public boolean createProductIfNotExist(Product product, Repository repository){
        if (product == null || product.getProd_key() == null || product.getProd_key().trim().equals("") ||
                product.getName() == null || product.getName().trim().equals("") ||
                product.getLanguage() == null ){
            System.out.println("ERROR in createProductIfNotExist. Some mandatroy fields are empty!");
            return false;
        }
        if (productDao.existAlready(product.getLanguage(), product.getProd_key())){
            if (repository != null && !productDao.doesRepositoryExistAlready(product.getLanguage(), product.getProd_key(), repository.getSrc()))
                productDao.addNewRepository(product.getLanguage(), product.getProd_key(), repository);
            return false;
        } else {
            productDao.create(product);
            System.out.println("new product created: " + product.getProd_key());
            return true;
        }
    }

    public boolean createVersionIfNotExist(Product product, Version version, Repository repository){
        if (version.getProduct_key() == null || version.getVersion() == null || version.getVersion().trim().equals(""))
            return false;

        if (existProduct(product, version)){
            if (repository != null && !productDao.doesRepositoryExistAlready(product.getLanguage(), product.getProd_key(), repository.getSrc()))
                productDao.addNewRepository(product.getLanguage(), product.getProd_key(), repository);
            return false;
        }

        productDao.addNewVersion(product.getLanguage(), product.getProd_key(), version);
        System.out.println("product: " + product.getProd_key() + " has new version: " + version.getVersion());

        writeNotifications(product, version);

        publishToNewest(product, version);

        return true;
    }

    private boolean existProduct(Product product, Version version){
        boolean exist = false;
        boolean groupIdExists    = product.getGroupId()    != null && !product.getGroupId().isEmpty();
        boolean artifactIdExists = product.getArtifactId() != null && !product.getArtifactId().isEmpty();
        if (groupIdExists && artifactIdExists){
            exist = productDao.doesVersionExistAlreadyByGA (product.getGroupId().toLowerCase(), product.getArtifactId().toLowerCase(), version.getVersion());
        } else {
            exist = productDao.doesVersionExistAlready(product.getLanguage(), product.getProd_key(), version.getVersion());
        }
        return exist;
    }

    public int writeNotifications(Product product, Version version) {
        int count = 0 ;
        try{
            Product prod = refetchProduct(product);
            if (prod == null){
                String errMsg = "no product found for: " + product.getLanguage() + ":" + product.getProd_key();
                System.out.println(errMsg);
                logUtils.addError("error in ProductService.writeNotifications", errMsg, null);
                return 0;
            }

            List<ObjectId> userIds = prod.getUser_ids();
            if (userIds == null || userIds.isEmpty()){
                return 0;
            }

            for (ObjectId userId : userIds){
                Notification notification = new Notification();
                notification.setUserId(    userId );
                notification.setProductId( prod.getProductId() );
                notification.setVersionId( version.getVersion() );
                notificationDao.createNotification(notification);
                System.out.println("  -- New Notification for " + userId.toString() );
                count++;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logUtils.addError("error in CrawlerUtils.writeNotifications", ex.toString(), null);
        }
        return count;
    }

    public Product refetchProduct(Product product) throws Exception {
        Product prod = null;
        if (product.getGroupId() != null && !product.getGroupId().isEmpty() &&
                product.getArtifactId() != null && !product.getArtifactId().isEmpty()){
            prod = productDao.getByGA( product.getGroupId(), product.getArtifactId() );
            if (prod == null){
                prod = productDao.getByKey( product.getLanguage(), product.getProd_key());
            }
        } else {
            prod = productDao.getByKey( product.getLanguage(), product.getProd_key());
        }
        return prod;
    }

    public void publishToNewest(Product product, Version version){
        Newest newest = new Newest(product.getProd_key(), product.getLanguage(), product.getProd_type(), product.getName(), version.getVersion());
        newest.setProduct_id(product.getId());
        newestDao.create(newest);
    }

    public boolean parseDate(Version version){
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date_string = version.getReleased_string();
            if (date_string == null){
                return false;
            }
            date_string = date_string.replaceAll("T", " ");
            date_string = date_string.replaceAll("Z", "");
            date_string = date_string.replaceAll("\\+.*", "");
            Date date = df.parse(date_string);
            version.setReleased_at(date);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            logUtils.addError("error in CrawlerUtils.parseDate ", ex.toString(), null);
            return false;
        }
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setLogUtils(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

    public void setNotificationDao(INotificationDao notificationDao) {
        this.notificationDao = notificationDao;
    }

    public void setNewestDao(INewestDao newestDao) {
        this.newestDao = newestDao;
    }
}
