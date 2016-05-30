package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.versioneye.domain.Crawle;
import com.versioneye.domain.Error;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 4/19/13
 * Time: 4:44 PM
 */
public class ErrorDaoTest {

    private final static String CRAWLER_NAME = "Maven2Html";
    private final static String CRAWLER_VERSION = "1.0";
    private final static String REPOSITORY_SRC = "http://ibiblio.org";

    private Crawle crawle;
    private Error error;

    private ICrawleDao crawleDao;
    private IErrorDao errorDao;

    @Test
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        crawleDao = (ICrawleDao) context.getBean("crawleDao");
        errorDao = (IErrorDao) context.getBean("errorDao");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreateCrawle(){
        crawle = new Crawle();
        crawle.setCrawlerName(CRAWLER_NAME);
        crawle.setCrawlerVersion(CRAWLER_VERSION);
        crawle.setRepositorySrc(REPOSITORY_SRC);
        crawleDao.create(crawle);
    }

    @Test(dependsOnMethods = {"doCreateCrawle"})
    public void doCreateError(){
        Crawle cr = crawleDao.getById(crawle.getId());
        Assert.assertNotNull(cr);

        error = new Error();
        error.setCrawle_id(crawle.getId());
        error.setErrormessage("This is my error message");
        error.setSource("source_1");
        error.setSubject("subject_1");
        errorDao.create(error);
    }

    @Test(dependsOnMethods = {"doCreateError"})
    public void getById(){
        Error er = errorDao.getById(error.getId());
        Assert.assertNotNull(er);
        Assert.assertEquals(er.getCrawle_id(), crawle.getId());
    }

}
