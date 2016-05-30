package versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.Date;

public class Notification {

    public static final String ID = "_id";
    public static final String USER_ID = "user_id";
    public static final String PRODUCT_ID = "product_id";
    public static final String VERSION_ID = "version_id";
    public static final String READ = "read";
    public static final String SENT_EMAIL = "sent_email";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private ObjectId id;
    private ObjectId userId;
    private ObjectId productId;
    private String versionId;
    private boolean read = false;
    private boolean sentEmail = false;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(USER_ID, userId);
        doc.put(PRODUCT_ID, productId);
        doc.put(VERSION_ID, versionId);
        doc.put(READ, read);
        doc.put(SENT_EMAIL, sentEmail);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (ObjectId)object.get(ID) );
        setUserId((ObjectId) object.get(USER_ID));
        setProductId((ObjectId) object.get(PRODUCT_ID));
        setVersionId((String) object.get(VERSION_ID));
        setRead((Boolean) object.get(READ));
        setSentEmail((Boolean) object.get(SENT_EMAIL));
        setCreatedAt((Date) object.get("created_at"));
        setUpdatedAt((Date) object.get("updated_at"));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public String getVersionId() {
        return versionId;
    }

    public void setVersionId(String versionId) {
        this.versionId = versionId;
    }

    public boolean isSentEmail() {
        return sentEmail;
    }

    public void setSentEmail(boolean sentEmail) {
        this.sentEmail = sentEmail;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Timestamp getUpdatedAtTimeStamp() {
        return new Timestamp(updatedAt.getTime());
    }

    public Timestamp getCreatedAtTimeStamp() {
        return new Timestamp(createdAt.getTime());
    }

}