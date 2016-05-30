package com.versioneye.service;

import org.htmlcleaner.TagNode;
import com.versioneye.domain.Developer;
import com.versioneye.domain.Product;
import com.versioneye.persistence.IDeveloperDao;
import com.versioneye.utils.HttpUtils;
import com.versioneye.utils.LogUtils;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 6:31 PM
 */
public class DeveloperService {

    private IDeveloperDao developerDao;
    private HttpUtils httpUtils;
    private LogUtils logUtils;

    public void createDevelopersIfNotExist(TagNode pom, HashMap<String, String> properties, Product product){
        try{
            Object[] developers = pom.evaluateXPath("//project/developers/developer");
            for (Object dev : developers){
                if (dev instanceof TagNode){
                    TagNode developerNode = (TagNode) dev;
                    String devId = httpUtils.getSingleValue(developerNode.evaluateXPath("/id"), properties);
                    String name = httpUtils.getSingleValue(developerNode.evaluateXPath("/name"), properties);
                    String email = httpUtils.getSingleValue(developerNode.evaluateXPath("/email"), properties);
                    String organization = httpUtils.getSingleValue(developerNode.evaluateXPath("/organization"), properties);
                    String organizationUrl = httpUtils.getSingleValue(developerNode.evaluateXPath("/organizationUrl"), properties);
                    String timezone = httpUtils.getSingleValue(developerNode.evaluateXPath("/timezone"), properties);

                    Developer developer = new Developer(product.getLanguage(), product.getProd_key(), product.getVersion(), devId, name,
                            email, "", "", organization, organizationUrl, timezone);

                    if (!developerDao.doesExistAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), name))
                        developerDao.create(developer);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logUtils.addError("CrawlerUtils.createDevelopersIfNotExist", ex.toString(), null);
        }
    }

    public void setDeveloperDao(IDeveloperDao developerDao) {
        this.developerDao = developerDao;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void setLogUtils(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

}
