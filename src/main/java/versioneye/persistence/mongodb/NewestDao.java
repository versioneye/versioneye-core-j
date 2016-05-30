package versioneye.persistence.mongodb;

import com.mongodb.DBCollection;
import versioneye.domain.Newest;
import versioneye.persistence.INewestDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 4/8/12
 * Time: 5:38 PM
 *
 */
public class NewestDao implements INewestDao {

    private static final String NEWEST = "newests";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(NEWEST);
    }

    public void create(Newest newest) {
        DBCollection newestCollection = getCollection();
        newestCollection.insert(newest.getDBObject());
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}