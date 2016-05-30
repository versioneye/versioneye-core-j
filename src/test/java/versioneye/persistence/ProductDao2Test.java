package versioneye.persistence;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;
import versioneye.domain.Product;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 10/9/12
 * Time: 4:29 PM
 *
 */
public class ProductDao2Test {

    private IProductDao productDao;
    private static ApplicationContext context;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        productDao = (IProductDao) context.getBean("productDao");
    }

    @Test(dependsOnMethods = {"init"})
    public void fetchProducts(){
        List<Product> products = productDao.fetchProductsWithEmptyReleaseString("Ruby");
        System.out.println("size: " + products.size());
        for (Product product : products) {
            System.out.println("product.name: " + product.getName());
        }
        System.out.println("size: " + products.size());
    }

}
