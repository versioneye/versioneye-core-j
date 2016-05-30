package com.versioneye.persistence.mongodb;

import com.mongodb.DBCollection;
import com.versioneye.domain.Versionchange;
import com.versioneye.persistence.IVersionchangeDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 3:44 PM
 */
public class VersionchangeDao implements IVersionchangeDao {

    private static final String VERSIONCHANGE = "versionchanges";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(VERSIONCHANGE);
    }

    public Versionchange create(Versionchange versionchange) {
        DBCollection collection = getCollection();
        collection.insert(versionchange.getDBObject());
        return versionchange;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}