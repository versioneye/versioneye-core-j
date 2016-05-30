package versioneye.persistence;

import versioneye.domain.Newest;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 4/8/12
 * Time: 5:37 PM
 */
public interface INewestDao {

    void create(Newest newest);

}
