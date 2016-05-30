package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.versioneye.domain.Versionlink;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 3/15/12
 * Time: 4:32 PM
 *
 */
public class VersionlinkDaoTest {

    private static String KEY = "/group/arti5";

    private IVersionlinkDao versionlinkDao;
    private DomainFactory domainFactory;
    private static ApplicationContext context;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        versionlinkDao = (IVersionlinkDao) context.getBean("versionlinkDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @BeforeClass
    public void before(){
        KEY += String.valueOf(Math.random());
    }

    @Test(dependsOnMethods = {"init"})
    public void saveAndFind(){
        String src = "http://homepage.de";
        Versionlink link = new Versionlink("Java", KEY, "Homepage", src, "1.0");
        versionlinkDao.create(link);
        Assert.assertTrue(versionlinkDao.doesLinkExistArleady("Java", KEY, src));
        Assert.assertFalse(versionlinkDao.doesLinkExistArleady("Java", KEY, src + "ss"));
    }

}
