package com.versioneye.persistence;


import com.mongodb.DBCollection;
import com.versioneye.domain.GlobalSetting;

public interface IGlobalSettingDao {

    public DBCollection getCollection();

    GlobalSetting getBy(String environment, String key) throws Exception;

    boolean setValue(String environment, String key, String value) throws Exception;

}
