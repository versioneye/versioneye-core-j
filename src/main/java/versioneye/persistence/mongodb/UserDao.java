package versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import versioneye.domain.User;
import versioneye.persistence.IUserDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 3/10/12
 * Time: 9:44 AM
 */
public class UserDao implements IUserDao {

    private static final String USERS = "users";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(USERS);
    }

    public User create(User user) {
        DBCollection followers = getCollection();
        followers.insert(user.getDBObject());
        return user;
    }

    public User getById(String id) {
        BasicDBObject match = new BasicDBObject();
        match.put(User.ID, new ObjectId(id));
        DBCollection users = getCollection();
        DBCursor cursor = users.find(match);
        if (cursor.hasNext()){
            DBObject userObj = cursor.next();
            User user = new User();
            user.updateFromDbObject(userObj);
            return user;
        }
        return null;
    }

    public User getByUsername(String username) {
        BasicDBObject match = new BasicDBObject();
        match.put(User.USERNAME, username);
        DBCollection users = getCollection();
        DBCursor cursor = users.find(match);
        if (cursor.hasNext()){
            DBObject userObj = cursor.next();
            User user = new User();
            user.updateFromDbObject(userObj);
            return user;
        }
        return null;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}