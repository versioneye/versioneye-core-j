package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import com.versioneye.domain.Notification;
import com.versioneye.persistence.INotificationDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 3/9/12
 * Time: 8:57 PM
 */
public class NotificationDao implements INotificationDao {

    private static final String NOTIFICATIONS = "notifications";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(NOTIFICATIONS);
    }

    public Notification createNotification(Notification notification) {
        DBCollection notifications = getCollection();
        notifications.insert(notification.getDBObject());
        return notification;
    }

    public Notification getBy(ObjectId userId, ObjectId productId, String versionId){
        Notification notification = null;
        DBCollection notifications = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Notification.USER_ID, userId);
        match.put(Notification.PRODUCT_ID, productId);
        match.put(Notification.VERSION_ID, versionId);
        DBCursor cursor = notifications.find(match);
        if (!cursor.hasNext())
            return notification;
        DBObject noti = cursor.next();
        notification = new Notification();
        notification.updateFromDbObject(noti);
        return notification;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}