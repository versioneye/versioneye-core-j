package versioneye.persistence;

import versioneye.domain.Dependency;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 5/17/12
 * Time: 11:31 PM
 *
 */
public interface IDependencyDao  {

    void create(Dependency dependency);

    List<Dependency> find(String language, String prodKey, String version);

    boolean existAlready(String language, String prodKey, String prod_version, String depProdKey, String version);

    boolean deleteDependencies(String language, String prodKey, String prod_version);

}