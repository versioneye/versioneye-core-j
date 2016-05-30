package versioneye.service;

import versioneye.domain.Versionlink;
import versioneye.persistence.IVersionlinkDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 5:12 PM
 */
public class VersionLinkService {

    protected IVersionlinkDao versionlinkDao;

    public void createLinkIfNotExist(String language, String prodKey, String version, String name, String url){
        if (url == null || url.trim().equals(""))
            return ;
        Versionlink link = new Versionlink(language, prodKey, name, url);
        if (version != null)
            link.setVersion_id(version);
        convertAndPersistIfNotExist(prodKey, link);
    }

    public void convertAndPersistIfNotExist(String prodKey, Versionlink versionlink){
        String link = versionlink.getLink();
        String new_link = convert_git_github_to_http(link);
        if (!new_link.equals(link)){
            Versionlink versionlink_new = new Versionlink(versionlink.getLanguage(), versionlink.getProduct_key(), versionlink.getName(), versionlink.getLink(), versionlink.getVersion_id());
            createProductLinkIfNotExist(prodKey, versionlink_new);
        }
        createProductLinkIfNotExist(prodKey, versionlink);
    }

    public void createProductLinkIfNotExist(String prodKey, Versionlink link){
        if (link.getVersion_id() != null && versionlinkDao.doesLinkExistArleady( link.getLanguage(), prodKey, link.getVersion_id(), link.getLink(), link.getName() ) ){
            return ;
        }
        if (versionlinkDao.doesLinkExistArleady( link.getLanguage(), prodKey, link.getLink() ) ){
            return ;
        }
        versionlinkDao.create(link);
    }

    protected String convert_git_github_to_http(String link){
        if (link.startsWith("git://github.com") && link.endsWith(".git")){
            String new_link = link;
            new_link = new_link.replaceFirst("git://github.com", "https://github.com");
            new_link = new_link.replace(".git", "");
            return new_link;
        }
        return link;
    }

    public void setVersionlinkDao(IVersionlinkDao versionlinkDao) {
        this.versionlinkDao = versionlinkDao;
    }

}
