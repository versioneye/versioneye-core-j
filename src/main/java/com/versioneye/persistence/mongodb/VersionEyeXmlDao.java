package com.versioneye.persistence.mongodb;

import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.VersionEyeXml;
import com.versioneye.persistence.IVersionEyeXmlDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/9/12
 * Time: 9:28 AM
 */
public class VersionEyeXmlDao implements IVersionEyeXmlDao {

    private static final String VERSIONEYEXML = "versioneyexmls";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(VERSIONEYEXML);
    }

    public List<VersionEyeXml> getAll() {
        List<VersionEyeXml> result = new ArrayList<VersionEyeXml>();
        DBCursor cursor = getCollection().find();
        while (cursor.hasNext()){
            DBObject object = cursor.next();
            VersionEyeXml xml = new VersionEyeXml();
            xml.updateFromDbObject(object);
            if (xml.getApproved() == true)
                result.add(xml);
        }
        return result;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}
