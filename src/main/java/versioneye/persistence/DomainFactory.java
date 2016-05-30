package versioneye.persistence;

import versioneye.domain.Product;
import versioneye.domain.User;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 1/3/12
 * Time: 8:02 PM
 */

public class DomainFactory{

    private Random rand = new Random();
    private IUserDao userDao;
    private IProductDao productDao;

    public User generateUser(){
        long count = rand.nextInt(1000 - 1 + 1) + 1;;
        User user = new User();
        user.setEmail(count+"meine@mail.de");
        user.setFullname(count + "FullName");
        user.setEncryptedPassword(count + "password");
        user.setSalt("salt");
        user.setUsername(count+"username");
        user = userDao.create(user);
        user = userDao.getByUsername(user.getUsername());
        return user;
    }

    public Product generateProduct(){
        long count = rand.nextInt(1000 - 1 + 1) + 1;;
        Product product = new Product();
        product.setName(count+"ProdName");
        product.setProd_key(count+"ProdKey");
        product.setGroupId(count+"groupid");
        product.setArtifactId(count+"artifactId");
        product.setProd_type("Maven2");
        product.setLanguage("Java");
        productDao.create(product);
        try {
            product = productDao.getByKey(product.getLanguage(), product.getProd_key());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return product;
    }

    public void generateFollower(User user, Product product){
        productDao.addNewUser(user.getId(), product.getLanguage(), product.getProd_key());
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

}
