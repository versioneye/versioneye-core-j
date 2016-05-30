package com.versioneye.persistence;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GlobalSettingDaoTest {

    private IGlobalSettingDao globalSettingDao;

    @Test
    public void init() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        globalSettingDao = (IGlobalSettingDao) context.getBean("globalSettingDao");
        globalSettingDao.getCollection().drop();
    }

    @Test(dependsOnMethods = {"init"})
    public void doReadAndWrite() throws Exception {
        globalSettingDao.setValue("test", "halLE", "luja");
        String value = globalSettingDao.getBy("test", "halle").getValue();
        Assert.assertEquals(value, "luja");
    }

}