package versioneye.persistence;

import versioneye.domain.License;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 7/29/13
 * Time: 2:32 PM
 */
public interface ILicenseDao {

    void create(License license);

    List<License> getBy(String language, String key, String version) throws Exception;

    boolean existAlready(String language, String prodKey, String version, String name, String url);

}
