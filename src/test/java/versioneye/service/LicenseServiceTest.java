package versioneye.service;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import versioneye.domain.Product;
import versioneye.persistence.DomainFactory;
import versioneye.persistence.ILicenseDao;
import versioneye.persistence.IProductDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/19/13
 * Time: 7:18 PM
 */
public class LicenseServiceTest {

    private static ApplicationContext context;
    private LicenseService licenseService;
    private ILicenseDao licenseDao;
    private DomainFactory domainFactory;


    @Test
    public void init(){
        context   = new ClassPathXmlApplicationContext("applicationContext.xml");
        licenseService = (LicenseService) context.getBean("licenseService");
        licenseDao = (ILicenseDao) context.getBean("licenseDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreate(){
        Product product = domainFactory.generateProduct();
        String name = "MIT";
        String url = "http://www.mit_license.com";
        Assert.assertFalse(licenseDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), name, url));
        licenseService.createLicenseIfNotExist(product, name, url, null, null);
        Assert.assertTrue(licenseDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), name, url));
    }


}
