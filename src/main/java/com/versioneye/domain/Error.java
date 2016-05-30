package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.Date;

public class Error {

    public static final String ID = "_id";
    public static final String ERRORS = "error_messages";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String SUBJECT = "subject";
    public static final String ERRORMESSAGE = "errormessage";
    public static final String SOURCE = "source";
    public static final String CRAWLE_ID = "crawle_id";

    private ObjectId id;
    private String subject;
    private String errormessage;
    private String source;
    private ObjectId crawle_id;
    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public Error(){
        id = new ObjectId();
    }

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(ID, id);
        doc.put(SUBJECT, subject);
        doc.put(ERRORMESSAGE, errormessage);
        doc.put(SOURCE, source);
        doc.put(CRAWLE_ID, crawle_id);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
    }

    public void updateFromDBObject(DBObject object){
        id = (ObjectId) object.get(ID);
        subject = (String) object.get(SUBJECT);
        errormessage = (String) object.get(ERRORMESSAGE);
        source = (String) object.get(SOURCE);
        crawle_id = (ObjectId) object.get(CRAWLE_ID);
        createdAt = (Date) object.get(CREATED_AT);
        changedAt = (Date) object.get(UPDATED_AT);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getCrawle_id() {
        return crawle_id;
    }

    public void setCrawle_id(ObjectId crawle_id) {
        this.crawle_id = crawle_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getErrormessage() {
        return errormessage;
    }

    public void setErrormessage(String errormessage) {
        this.errormessage = errormessage;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Timestamp getCreatedAtTimeStamp() {
        return new Timestamp(createdAt.getTime());
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public Timestamp getChangedAtTimeStamp() {
        return new Timestamp(changedAt.getTime());
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

}