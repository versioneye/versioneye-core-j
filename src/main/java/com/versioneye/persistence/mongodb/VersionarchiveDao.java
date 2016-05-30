package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.versioneye.domain.Versionarchive;
import com.versioneye.domain.Versionlink;
import com.versioneye.persistence.IVersionarchiveDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 3:53 PM
 */
public class VersionarchiveDao implements IVersionarchiveDao {

    private static final String VERSIONARCHIVE = "versionarchives";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(VERSIONARCHIVE);
    }

    public Versionarchive create(Versionarchive versionarchive) {
        DBCollection collection = getCollection();
        collection.insert(versionarchive.getDBObject());
        return versionarchive;
    }

    public boolean doesLinkExistArleady(String language, String prod_key, String version, String src){
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.VERSION_ID, version);
        match.put(Versionlink.LINK, src);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean doesArchiveExistArleadyByName(String language, String prod_key, String version, String name) {
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.VERSION_ID, version);
        match.put(Versionlink.NAME, name);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean doesArchiveExistArleady(String language, String prod_key, String version, String name, String url) {
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.VERSION_ID, version);
        match.put(Versionlink.NAME, name);
        match.put(Versionlink.LINK, url);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean removeArchive(String language, String prod_key, String version, String name) {
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.VERSION_ID, version);
        match.put(Versionlink.NAME, name);
        getCollection().remove(match);
        return true;
    }

    public void drop(){
        getCollection().drop();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}