package versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import versioneye.domain.Versionarchive;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/10/13
 * Time: 2:58 PM
 */
public class VersionarchiveDaoTest {

    private static String KEY = "/group/arti5";

    private IVersionarchiveDao versionarchiveDao;
    private static ApplicationContext context;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        versionarchiveDao = (IVersionarchiveDao) context.getBean("versionarchiveDao");
    }

    @BeforeClass
    public void before(){
        KEY += String.valueOf(Math.random());
    }

    @Test(dependsOnMethods = {"init"})
    public void saveAndFind(){
        String src = "http://homepage.de";
        Versionarchive archive = new Versionarchive("Java", KEY, "porno", src);
        archive.setVersion_id("1.0.0");

        versionarchiveDao.create(archive);

        Assert.assertTrue(versionarchiveDao.doesArchiveExistArleady("Java", KEY, "1.0.0", "porno", src));
        Assert.assertFalse(versionarchiveDao.doesArchiveExistArleady("JavO", KEY, "1.0.0", "porno", src));

        Assert.assertTrue(versionarchiveDao.doesArchiveExistArleadyByName("Java", KEY, "1.0.0", "porno"));
        Assert.assertFalse(versionarchiveDao.doesArchiveExistArleadyByName("Java", KEY, "1.0.0", "panda"));

        Assert.assertTrue(versionarchiveDao.doesLinkExistArleady("Java", KEY, "1.0.0", src));
        Assert.assertFalse(versionarchiveDao.doesLinkExistArleady("Java", KEY, "1.0.1", src));

        versionarchiveDao.removeArchive("Java", KEY, "1.0.0", "porno");

        Assert.assertFalse(versionarchiveDao.doesLinkExistArleady("Java", KEY, "1.0.0", src));
        Assert.assertFalse(versionarchiveDao.doesArchiveExistArleadyByName("Java", KEY, "1.0.0", "porno"));
        Assert.assertFalse(versionarchiveDao.doesArchiveExistArleady("Java", KEY, "1.0.0", "porno", src));
    }

}
