package versioneye.utils;

import org.htmlcleaner.TagNode;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

import java.io.Reader;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 7:00 PM
 */
public class HttpUtilsTest {

    private static ApplicationContext context;
    private HttpUtils httpUtils;

    @Test
    public void init(){
        context   = new ClassPathXmlApplicationContext("applicationContext.xml");
        httpUtils = (HttpUtils)  context.getBean("httpUtils");
    }

    @Test(dependsOnMethods = {"init"})
    public void getHttpResponse() throws Exception {
        String response = httpUtils.getHttpResponse("http://versioneye.com/");
        assert response != null;
    }

    @Test(dependsOnMethods = {"init"})
    public void getResultReader() throws Exception {
        Reader reader = httpUtils.getResultReader("http://search.maven.org/solrsearch/select?q=g:%22acegisecurity%22+AND+a:%22acegi-security-adapters%22&core=gav&rows=20&wt=json");
        assert reader != null;
    }

    @Test(dependsOnMethods = {"init"})
    public void getResponseCode() throws Exception {
        int code = httpUtils.getResponseCode("https://repo1.maven.org/maven2/net/sf/doolin/Doolin-Context/0.10.2/Doolin-Context-0.10.2.pom");
        assert code == 200;
    }

    @Test(dependsOnMethods = {"init"})
    public void getResponseCodeNegative() throws Exception {
        int code = httpUtils.getResponseCode("http://search.maven.org/remotecontent?filepath=net/sf/doolin/Doolin-Context/0.10.2/Doolin-Context-0.10.2.pom_f");
        assert code != 200;
    }

    @Test(dependsOnMethods = {"init"})
    public void getPageForResource() throws Exception {
        TagNode node = httpUtils.getPageForResource("http://jcenter.bintray.com/au/com/permeance/permeance-parent/0.6/permeance-parent-0.6.pom");
        assert node != null;
    }



//    @Test(dependsOnMethods = {"init"})
//    public void getResultReaderWithAuth() throws Exception {
//        String url = "http://localhost:8081/artifactory/api/storage/remote-repos?list&deep=1&listFolders=0&mdTimestamps=1";
//        String response = httpUtils.getHttpResponse(url, "admin", "password");
//        System.out.println(response);
//        assert response != null;
//    }

}
