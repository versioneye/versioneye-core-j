package com.versioneye.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.versioneye.domain.Product;
import com.versioneye.persistence.IVersionarchiveDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 7:34 PM
 */
public class ArchiveServiceTest {

    private static ApplicationContext context;
    private com.versioneye.service.ArchiveService archiveService;
    private IVersionarchiveDao versionarchiveDao;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        archiveService = (com.versioneye.service.ArchiveService) context.getBean("archiveService");
        versionarchiveDao = (IVersionarchiveDao) context.getBean("versionarchiveDao");

        versionarchiveDao.drop();
    }

    @Test(dependsOnMethods = {"init"})
    public void createArchivesIfNotExist(){
        boolean exist = versionarchiveDao.doesArchiveExistArleady("Java", "junit/junit", "4.9", "junit", "http://gradle.artifactoryonline.com/gradle/libs/junit/junit/4.9/junit-4.9.pom" );
        Assert.assertFalse(exist);
        Product product = new Product();
        product.setProd_key("junit/junit");
        product.setGroupId("junit");
        product.setArtifactId("junit");
        product.setVersion("4.9");
        archiveService.createArchivesIfNotExist(product, "http://gradle.artifactoryonline.com/gradle/libs/junit/junit/4.9/junit-4.9.pom");
        exist = versionarchiveDao.doesArchiveExistArleady("Java", "junit/junit", "4.9", "junit", "http://gradle.artifactoryonline.com/gradle/libs/junit/junit/4.9/junit-4.9.pom" );
        Assert.assertFalse(exist);
    }

}
