package com.versioneye.persistence;

import com.versioneye.domain.Versionarchive;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 3:52 PM
 */
public interface IVersionarchiveDao {

    Versionarchive create(Versionarchive versionarchive);

    boolean doesLinkExistArleady(String language, String prod_key, String version, String src);

    boolean doesArchiveExistArleadyByName(String language, String prod_key, String version, String name);

    boolean doesArchiveExistArleady(String language, String prod_key, String version, String name, String url);

    boolean removeArchive(String language, String prod_key, String version, String name);

    void drop();

}
