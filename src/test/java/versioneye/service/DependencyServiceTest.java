package versioneye.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import versioneye.domain.Dependency;
import versioneye.domain.Product;
import versioneye.persistence.DomainFactory;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 7:49 PM
 */
public class DependencyServiceTest {

    private static ApplicationContext context;
    private DomainFactory domainFactory;
    private DependencyService dependencyService;

    @Test
    public void init(){
        context   = new ClassPathXmlApplicationContext("applicationContext.xml");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
        dependencyService = (DependencyService) context.getBean("dependencyService");
    }

    @Test(dependsOnMethods = {"init"})
    public void updateKnown(){
        Dependency dependency = new Dependency(null, null, null, null, null, null);
        dependency.setDepProdKey("fasfsjjjjasgbasg");
        Assert.assertNull(dependency.getKnown());
        dependencyService.updateKnownStatus(dependency);
        Assert.assertFalse(dependency.getKnown().booleanValue());
    }

    @Test(dependsOnMethods = {"init"})
    public void updateKnown_2(){
        Product product = domainFactory.generateProduct();
        Dependency dependency = new Dependency(null, null, null, null, null, null);
        dependency.setDepProdKey(product.getProd_key());
        dependency.setLanguage(product.getLanguage());
        Assert.assertNull(dependency.getKnown());
        dependencyService.updateKnownStatus(dependency);
        Assert.assertTrue(dependency.getKnown().booleanValue());
    }

    @Test(dependsOnMethods = {"updateKnown_2"})
    public void updateKnown_3(){
        Product product = domainFactory.generateProduct();
        Dependency dependency = new Dependency(null, null, null, null, null, null);
        dependency.setDepProdKey(product.getProd_key());
        dependency.setLanguage("Clojure");
        Assert.assertNull(dependency.getKnown());
        dependencyService.updateKnownStatus(dependency);
        Assert.assertTrue(dependency.getKnown().booleanValue());
        Assert.assertEquals(dependency.getLanguage(), "Java");
    }

}
