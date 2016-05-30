package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.versioneye.domain.License;
import com.versioneye.domain.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 7/29/13
 * Time: 2:42 PM
 */
public class LicenseDaoTest {

    private ILicenseDao licenseDao;
    private DomainFactory domainFactory;

    private Product product;


    @Test
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        licenseDao = (ILicenseDao) context.getBean("licenseDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreateLicense(){
        product = domainFactory.generateProduct();
        License license = new License();
        license.setLanguage(product.getLanguage());
        license.setProd_key(product.getProd_key());
        license.setVersion(product.getVersion());
        license.setName("Name");
        license.setUrl("http://www.mit.de");
        license.setComments("This is a comment");
        license.setDistributions("Distri");
        licenseDao.create(license);
    }

    @Test(dependsOnMethods = {"doCreateLicense"})
    public void doExistAlready() throws Exception {
        Assert.assertTrue(licenseDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), "Name", "http://www.mit.de"));
        Assert.assertFalse(licenseDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), "Name", "http://"));
    }

    @Test(dependsOnMethods = {"doCreateLicense"})
    public void doFindDependencies() throws Exception {
        List<License> licenses = licenseDao.getBy(product.getLanguage(), product.getProd_key(), product.getVersion());
        Assert.assertNotNull(licenses);
        Assert.assertTrue(!licenses.isEmpty());
        Assert.assertEquals(licenses.size(), 1);
        License license = licenses.get(0);
        Assert.assertEquals(license.getName(), "Name");
        Assert.assertEquals(license.getUrl(), "http://www.mit.de");
        Assert.assertEquals(license.getComments(), "This is a comment");
        Assert.assertEquals(license.getDistributions(), "Distri");
    }

}
