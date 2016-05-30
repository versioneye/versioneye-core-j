package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.versioneye.domain.Notification;
import com.versioneye.domain.Product;
import com.versioneye.domain.User;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 12/9/11
 * Time: 5:35 PM
 *
 */
public class NotificationDaoTest {

    private INotificationDao notificationDao;
    private IProductDao productDao;
    private IUserDao userDao;
    private DomainFactory domainFactory;
    private static ApplicationContext context;

    private Random rand = new Random();

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        notificationDao = (INotificationDao) context.getBean("notificationDao");
        productDao = (IProductDao) context.getBean("productDao");
        userDao = (IUserDao) context.getBean("userDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @Test(dependsOnMethods = {"init"})
    public void createNotification() throws Exception{
        Product product = new Product();
        product.setName("Name");
        product.setGroupId("group1333");
        product.setArtifactId("arti1_asgfa_1");
        product.setLanguage("Java");
        String key = product.getGroupId() + "/" + product.getArtifactId() + "_" + String.valueOf(Math.random());
        product.setProd_key( key );
        product.setLink("www.server.de/group/arti/");
        productDao.create(product);
        Product prod = productDao.getByKey(product.getLanguage(), product.getProd_key());

        User user = domainFactory.generateUser();
        User userFinded = userDao.getByUsername(user.getUsername());

        long rand1 = rand.nextInt(1000 - 1 + 1) + 1;
        Notification notification = new Notification();

        String versionId = rand1+"asgfasfga";
        notification.setUserId( userFinded.getId() );
        notification.setProductId(prod.getProductId());
        notification.setVersionId(versionId);
        notificationDao.createNotification(notification);
        Notification notificationFinded = notificationDao.getBy( userFinded.getId(), prod.getProductId(), versionId );
        Assert.assertNotNull(notificationFinded.getId());
        Assert.assertEquals(notificationFinded.getUserId(), userFinded.getId());
        Assert.assertEquals(notificationFinded.getProductId(), prod.getProductId());
        Assert.assertEquals(notificationFinded.getVersionId(),versionId );
    }

}