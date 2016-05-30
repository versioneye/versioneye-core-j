package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 9/5/12
 * Time: 11:33 AM
 *
 */
public class Projectdependency {

    public static final String ID = "_id";
    public static final String PROD_KEY = "prod_key";
    public static final String GROUPID = "group_id";
    public static final String ARTIFACTID = "artifact_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private ObjectId id;
    private String prodKey;
    private String groupId;
    private String artifactId;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(PROD_KEY, prodKey.toLowerCase());
        doc.put(GROUPID, groupId);
        doc.put(ARTIFACTID, artifactId);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (ObjectId)object.get(ID) );
        setGroupId( (String) object.get(GROUPID));
        setArtifactId((String) object.get(ARTIFACTID));
        setProdKey((String) object.get(PROD_KEY));
        setCreatedAt((Date) object.get(CREATED_AT));
        setUpdatedAt((Date) object.get(UPDATED_AT));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getProdKey() {
        return prodKey;
    }

    public void setProdKey(String prodKey) {
        this.prodKey = prodKey;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
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

}