package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.sql.Timestamp;
import java.util.Date;


public class User {

    public static final String ID = "_id";
    public static final String FULLNAME = "fullname";
    public static final String USERNAME = "username";
    public static final String EMAIL = "email";
    public static final String ENCRYPTED_PASSWORD = "encrypted_password";
    public static final String SALT = "salt";
    public static final String ADMIN = "admin";
    public static final String FB_ID = "fb_id";
    public static final String FB_TOKEN = "fb_token";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private ObjectId id;
    private String fullname;
    private String username;
    private String email;
    private String encryptedPassword;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String salt;
    private boolean admin;
    private String fbId;
    private String fbToken;

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(FULLNAME, fullname);
        doc.put(USERNAME, username);
        doc.put(EMAIL, email);
        doc.put(ENCRYPTED_PASSWORD, encryptedPassword);
        doc.put(SALT, salt);
        doc.put(ADMIN, admin);
        doc.put(FB_ID, fbId);
        doc.put(FB_TOKEN, fbToken);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (ObjectId)object.get(ID) );
        setUsername( (String) object.get(USERNAME) );
        setFullname( (String) object.get(FULLNAME) );
        setEmail( (String) object.get(EMAIL) );
        setEncryptedPassword( (String) object.get(ENCRYPTED_PASSWORD) );
        setSalt( (String) object.get(SALT) );
        setAdmin( (Boolean) object.get(ADMIN) );
        setFbId( (String) object.get(FB_ID) );
        setFbToken( (String) object.get(FB_TOKEN) );
        setCreatedAt((Date) object.get("created_at"));
        setUpdatedAt((Date) object.get("updated_at"));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
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

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public String getFbToken() {
        return fbToken;
    }

    public void setFbToken(String fbToken) {
        this.fbToken = fbToken;
    }

    public Timestamp getCreatedAtTimeStamp() {
        return new Timestamp(createdAt.getTime());
    }

    public Timestamp getUpdatedAtTimeStamp() {
        return new Timestamp(updatedAt.getTime());
    }

}