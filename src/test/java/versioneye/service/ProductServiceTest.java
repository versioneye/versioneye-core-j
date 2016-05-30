package versioneye.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import versioneye.domain.Product;
import versioneye.domain.User;
import versioneye.domain.Version;
import versioneye.persistence.DomainFactory;
import versioneye.persistence.IProductDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 7:42 PM
 */
public class ProductServiceTest {

    private static ApplicationContext context;
    private DomainFactory domainFactory;
    private ProductService productService;
    private IProductDao productDao;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        productService = (ProductService) context.getBean("productService");
        domainFactory  = (DomainFactory) context.getBean("domainFactory");
        productDao     = (IProductDao) context.getBean("productDao");
    }

    @Test(dependsOnMethods = {"init"})
    public void writeNotifications(){
        Product product = domainFactory.generateProduct();
        User user  = domainFactory.generateUser();
        User user2 = domainFactory.generateUser();
        User user3 = domainFactory.generateUser();

        domainFactory.generateFollower(user,  product);
        domainFactory.generateFollower(user2, product);
        domainFactory.generateFollower(user3, product);

        Version version = new Version();
        version.setProduct_key(product.getProd_key());
        version.setVersion("1.0");

        int notifications = productService.writeNotifications(product, version);
        Assert.assertEquals(notifications, 3);
        productDao.remove( product.getDBObject() );
    }

}
