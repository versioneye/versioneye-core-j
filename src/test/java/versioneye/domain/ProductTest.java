package versioneye.domain;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 3/30/12
 * Time: 12:19 PM
 *
 */
public class ProductTest {

    @Test
    public void getProdKeyUrl(){
        Product product = new Product();
        product.setProd_key("org.junit/junit");
        String url = product.getProdKeyUrl();
        assert url.equals("org~junit--junit");
    }

    @Test
    public void getVersion(){
        Product product = new Product();
        product.setProd_key("orgi.junit/junit");

        Version version = new Version();
        version.setVersion("1.1");
        product.addVersion(version);

        Version version_2 = new Version();
        version_2.setVersion("1.2");
        product.addVersion(version_2);

        Version ver_1 = product.getVersion("1.1");
        Assert.assertNotNull(ver_1);

        Version ver_2 = product.getVersion("1.2");
        Assert.assertNotNull(ver_2);

        Version ver_3 = product.getVersion("1.3");
        Assert.assertNull(ver_3);
    }

}
