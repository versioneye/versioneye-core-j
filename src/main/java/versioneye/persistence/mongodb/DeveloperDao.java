package versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import versioneye.domain.Developer;
import versioneye.persistence.IDeveloperDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 6/2/12
 * Time: 8:45 AM
 */
public class DeveloperDao implements IDeveloperDao {

    private static final String COLLECTION = "developers";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(COLLECTION);
    }

    public Developer create(Developer developer) {
        getCollection().insert(developer.getDBObject());
        return developer;
    }

    public boolean doesExistAlready(String language, String prodKey, String version, String name) {
        BasicDBObject match = new BasicDBObject();
        match.put(Developer.LANGUAGE, language);
        match.put(Developer.PROD_KEY, prodKey);
        match.put(Developer.VERSION, version);
        match.put(Developer.NAME, name);
        DBCollection dependencies = getCollection();
        DBCursor cursor = dependencies.find(match);
        return cursor.hasNext();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
