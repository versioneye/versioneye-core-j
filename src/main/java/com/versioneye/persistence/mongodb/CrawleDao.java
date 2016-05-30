package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import com.versioneye.domain.Crawle;
import com.versioneye.persistence.ICrawleDao;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 1/2/12
 * Time: 7:32 PM
 */
public class CrawleDao implements ICrawleDao {

    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(Crawle.CRAWLES);
    }

    public void create(Crawle crawle) {
        DBCollection products = getCollection();
        products.insert(crawle.getDBObject());
    }

    public void updateDates(ObjectId id, Date updated, Date duration) {
        BasicDBObject match = new BasicDBObject();
        match.put(Crawle.ID, id);
        DBCursor cursor = getCollection().find(match);
        if (!cursor.hasNext())
            return ;
        DBObject crawle = cursor.next();
        crawle.put(Crawle.UPDATED_AT, updated);
        crawle.put(Crawle.DURATION, duration);
        getCollection().save(crawle);
    }

    public Crawle getById(ObjectId id) {
        BasicDBObject match = new BasicDBObject();
        match.put(Crawle.ID, id);
        DBCursor cursor = getCollection().find(match);
        if (!cursor.hasNext())
            return null;
        DBObject crawle = cursor.next();
        Crawle cr = new Crawle();
        cr.updateFromDBObject(crawle);
        return cr;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
