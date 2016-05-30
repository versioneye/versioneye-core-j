package com.versioneye.persistence;

import org.bson.types.ObjectId;
import com.versioneye.domain.Notification;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 12/9/11
 * Time: 5:15 PM
 */
public interface INotificationDao {

    Notification createNotification(Notification notification);

    Notification getBy(ObjectId userId, ObjectId productId, String versionId);

}