package versioneye.persistence;

import versioneye.domain.VersionEyeXml;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/9/12
 * Time: 9:23 AM
 */
public interface IVersionEyeXmlDao {

    List<VersionEyeXml> getAll();

}
