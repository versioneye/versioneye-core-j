package versioneye.service;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.testng.annotations.Test;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 7:45 PM
 */
public class VersionLinkServiceTest {

    private static ApplicationContext context;
    private VersionLinkService versionLinkService;

    @Test
    public void init(){
        context = new ClassPathXmlApplicationContext("applicationContext.xml");
        versionLinkService = (VersionLinkService) context.getBean("versionLinkService");
    }

    @Test(dependsOnMethods = {"init"})
    public void convert_git_to_http(){
        String link = "git://github.com/verisoneye/naturalsorter.git";
        String new_link = versionLinkService.convert_git_github_to_http(link);
        assert new_link.equals("https://github.com/verisoneye/naturalsorter");
    }

    @Test(dependsOnMethods = {"init"})
    public void convert_git_to_http_not(){
        String link = "git://bitit.com/verisoneye/naturalsorter.git";
        String new_link = versionLinkService.convert_git_github_to_http(link);
        assert new_link.equals("git://bitit.com/verisoneye/naturalsorter.git");
    }

}
