package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.versioneye.domain.MavenRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/11/13
 * Time: 4:07 PM
 */
public class MavenRepositoryDaoTest {

    private IMavenRepostoryDao mavenRepostoryDao;

    @Test
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        mavenRepostoryDao = (IMavenRepostoryDao) context.getBean("mavenRepostoryDao");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreateAndLoad(){
        MavenRepository repo = new MavenRepository();
        repo.setName("central");
        repo.setUrl("http://repo1.maven.org/maven2");
        mavenRepostoryDao.create(repo);
        List<MavenRepository> repos = mavenRepostoryDao.loadAll();
        Assert.assertNotNull(repos);
        Assert.assertEquals(repos.size(), 1);
        MavenRepository repByName = mavenRepostoryDao.findByName("central");
        Assert.assertNotNull(repByName);
        Assert.assertEquals(repByName.getUrl(), repo.getUrl());
        MavenRepository repNull = mavenRepostoryDao.findByName("centralasgasg");
        Assert.assertNull(repNull);
        mavenRepostoryDao.remove(repo);
        repos = mavenRepostoryDao.loadAll();
        Assert.assertNotNull(repos);
        Assert.assertEquals(repos.size(), 0);
    }

}
