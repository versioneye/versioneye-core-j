package versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import versioneye.domain.Error;
import versioneye.persistence.IErrorDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 4/19/13
 * Time: 4:29 PM
 *
 */
public class ErrorDao implements IErrorDao {

    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(Error.ERRORS);
    }

    public void create(versioneye.domain.Error error) {
        DBCollection errors = getCollection();
        errors.insert(error.getDBObject());
    }

    public Error getById(ObjectId id) {
        BasicDBObject match = new BasicDBObject();
        match.put(Error.ID, id);
        DBCursor cursor = getCollection().find(match);
        if (!cursor.hasNext())
            return null;
        DBObject errorObj = cursor.next();
        Error error = new Error();
        error.updateFromDBObject(errorObj);
        return error;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;

    }
}
