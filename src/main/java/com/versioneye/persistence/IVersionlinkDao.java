package com.versioneye.persistence;

import com.versioneye.domain.Versionlink;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 1:45 PM
 */
public interface IVersionlinkDao {

    Versionlink create(final Versionlink versionlink);

    boolean doesLinkExistArleady(String language, String prod_key, String src);

    boolean doesLinkExistArleady(String language, String prod_key, String version, String src, String name);

}
