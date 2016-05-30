package versioneye.persistence;

import versioneye.domain.Developer;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 6/2/12
 * Time: 8:43 AM
 *
 */
public interface IDeveloperDao {

   Developer create(Developer developer);

   boolean doesExistAlready(String language, String prodKey, String version, String name);

}
