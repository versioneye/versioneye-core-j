package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

public class GlobalSetting {

    public static final String GLOBAL_SETTINGS = "global_settings";
    public static final String UID = "uid";
    public static final String ENVIRONMENT = "environment";
    public static final String KEY = "key";
    public static final String VALUE = "value";

    private String environment;
    private String key;
    private String value;

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(UID, new ObjectId());
        dbo.put(ENVIRONMENT, environment);
        dbo.put(KEY, key);
        dbo.put(VALUE, value);
        return dbo;
    }

    public void updateFromDbObject(DBObject object){
        setEnvironment( (String) object.get(ENVIRONMENT) );
        setKey((String) object.get(KEY));
        setValue((String) object.get(VALUE));
    }

    public String getEnvironment() {
        return environment;
    }

    public void setEnvironment(String environment) {
        this.environment = environment;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
