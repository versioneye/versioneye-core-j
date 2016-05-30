package com.versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import com.versioneye.domain.Crawle;

import java.util.Date;

import static org.testng.Assert.*;


/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 1/2/12
 * Time: 9:55 PM
 *
 */
public class CrawleDaoTest {

    private final static String CRAWLER_NAME = "Maven2Html";
    private final static String CRAWLER_VERSION = "1.0";
    private final static String REPOSITORY_SRC = "http://ibiblio.org";

    private Crawle crawle;
    private ICrawleDao crawleDao;

    @Test
    public void init(){
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        crawleDao = (ICrawleDao) context.getBean("crawleDao");
    }

    @Test(dependsOnMethods = {"init"})
    public void doCreate(){
        crawle = new Crawle();
        crawle.setCrawlerName(CRAWLER_NAME);
        crawle.setCrawlerVersion(CRAWLER_VERSION);
        crawle.setRepositorySrc(REPOSITORY_SRC);
        crawleDao.create(crawle);
    }

    @Test(dependsOnMethods = {"doCreate"})
    public void getById1(){
        Crawle cr = crawleDao.getById(crawle.getId());
        assertNotNull(cr);
        assertEquals(cr.getCrawlerName(), CRAWLER_NAME);
        assertEquals(cr.getCrawlerVersion(), CRAWLER_VERSION);
        assertEquals(cr.getRepositorySrc(), REPOSITORY_SRC);
        assertNull(cr.getUpdatedAt());
        assertNull(cr.getDuration());
    }

    @Test(dependsOnMethods = {"getById1"})
    public void doUpdate(){
        Date stop = new Date();
        Date duration = new Date( stop.getTime() - crawle.getCreatedAt().getTime() );
        crawleDao.updateDates(crawle.getId(), stop, duration);
    }

    @Test(dependsOnMethods = {"doUpdate"})
    public void getById2(){
        Crawle cr = crawleDao.getById(crawle.getId());
        assertNotNull(cr);
        assertNotNull(cr.getUpdatedAt());
        assertNotNull(cr.getDuration());
    }

}
