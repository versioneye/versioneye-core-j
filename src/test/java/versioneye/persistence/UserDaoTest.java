package versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.Assert;
import org.testng.annotations.Test;
import versioneye.domain.User;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 3/10/12
 * Time: 9:51 AM
 *
 */
public class UserDaoTest {

    private IUserDao userDao;
    private DomainFactory domainFactory;
    private static ApplicationContext context;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        userDao = (IUserDao) context.getBean("userDao");
        domainFactory = (DomainFactory) context.getBean("domainFactory");
    }

    @Test(dependsOnMethods = {"init"})
    public void saveAndFind(){
        User user = domainFactory.generateUser();
        User userFinded = userDao.getByUsername(user.getUsername());
        Assert.assertEquals(user.getEmail(), userFinded.getEmail());
        Assert.assertEquals(user.getFullname(),userFinded.getFullname() );
        User userFindById = userDao.getById(userFinded.getId().toString());
        Assert.assertEquals(user.getEmail(), userFindById.getEmail());
    }

}
