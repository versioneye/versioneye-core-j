package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.License;
import com.versioneye.persistence.ILicenseDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 7/29/13
 * Time: 2:32 PM
 */
public class LicenseDao implements ILicenseDao {

    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(License.LICENSES);
    }

    public void create(License license) {
        DBCollection licenses = getCollection();
        licenses.insert(license.getDBObject());
    }

    public List<License> getBy(String language, String key, String version) throws Exception{
        DBCollection licenses = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(License.LANGUAGE, language);
        match.put(License.PROD_KEY, key);
        match.put(License.VERSION, version);
        DBCursor cursor = licenses.find(match);
        List<License> licenseList = new ArrayList<License>();
        while (cursor.hasNext()){
            DBObject productDB = cursor.next();
            License license = new License();
            license.updateFromDbObject(productDB);
            licenseList.add(license);
        }
        return licenseList;
    }

    public boolean existAlready(String language, String prodKey, String version, String name, String url) {
        DBCollection licenses = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(License.LANGUAGE, language);
        match.put(License.PROD_KEY, prodKey);
        match.put(License.VERSION, version);
        match.put(License.NAME, name);
        match.put(License.URL, url);
        DBCursor cursor = licenses.find(match);
        if (cursor.hasNext())
            return true;
        else
            return false;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
