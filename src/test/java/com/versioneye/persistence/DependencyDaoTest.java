package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import com.versioneye.domain.Dependency;
import com.versioneye.domain.Product;

import static org.testng.Assert.*;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 7/29/13
 * Time: 2:05 PM
 */
public class DependencyDaoTest {

    private IDependencyDao dependencyDao;
    private DomainFactory domainFactory;

    private Product product;
    private Product product_2;
    private Dependency dependency;

    @Test
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        dependencyDao = (IDependencyDao) context.getBean("dependencyDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreateDependencies(){
        product = domainFactory.generateProduct();
        product_2 = domainFactory.generateProduct();
        dependency = new Dependency(product.getLanguage(), product.getProd_key(), product.getVersion(),
                product_2.getName(), product_2.getVersion(), product_2.getProd_key());
        dependencyDao.create(dependency);
    }

    @Test(dependsOnMethods = {"doCreateDependencies"})
    public void doFindDependencies(){
        assertTrue( dependencyDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(),
                product_2.getProd_key(), product_2.getVersion()) );
    }

    @Test(dependsOnMethods = {"doFindDependencies"})
    public void doNotFindDependencies(){
        assertTrue( !dependencyDao.existAlready("KungFu", product.getProd_key(), product.getVersion(),
                product_2.getProd_key(), product_2.getVersion()) );
    }

    @Test(dependsOnMethods = {"doNotFindDependencies"})
    public void doDeleteDependencies(){
        dependencyDao.deleteDependencies(product.getLanguage(), product.getProd_key(), product.getVersion());
        assertTrue( !dependencyDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(),
                product_2.getProd_key(), product_2.getVersion()) );
    }

}
