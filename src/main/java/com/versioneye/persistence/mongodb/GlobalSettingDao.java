package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.GlobalSetting;
import com.versioneye.persistence.IGlobalSettingDao;


public class GlobalSettingDao implements IGlobalSettingDao {

    private MongoDB mongoDB;

    public DBCollection getCollection(){
        return mongoDB.getDb().getCollection(GlobalSetting.GLOBAL_SETTINGS);
    }

    public GlobalSetting getBy(String environment, String key) throws Exception {
        if (environment == null || key == null){
            return null;
        }
        DBCollection licenses = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(GlobalSetting.ENVIRONMENT, environment);
        match.put(GlobalSetting.KEY, key.toUpperCase());
        DBCursor cursor = licenses.find(match);
        while (cursor.hasNext()){
            DBObject gsObj = cursor.next();
            GlobalSetting gs = new GlobalSetting();
            gs.updateFromDbObject(gsObj);
            return gs;
        }
        return null;
    }

    public boolean setValue(String environment, String key, String value) throws Exception {
        GlobalSetting gs = new GlobalSetting();
        gs.setEnvironment(environment);
        gs.setKey(key.toUpperCase());
        gs.setValue(value);
        DBCollection globalSettings = getCollection();
        globalSettings.insert(gs.getDBObject());
        return true;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}
