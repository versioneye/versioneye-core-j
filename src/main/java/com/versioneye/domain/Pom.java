package com.versioneye.domain;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Pom {

    public static final String POM = "poms";

    public static final String ID = "_id";
    public static final String URL = "url";

    private ObjectId id;
    private ObjectId url;


    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(ID, id);
        doc.put(URL, url);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (ObjectId)object.get(ID) );
        setUrl ((ObjectId) object.get(URL));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUrl() {
        return url;
    }

    public void setUrl(ObjectId url) {
        this.url = url;
    }
}
