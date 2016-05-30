package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/9/12
 * Time: 9:15 AM
 */
public class VersionEyeXml {

    public static final String PROD_KEY = "prod_key";
    public static final String URL = "url";
    public static final String USER_ID = "user_id";
    public static final String APPROVED = "approved";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String product_key;
    private String url;
    private Integer user_id;
    private Boolean approved;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(PROD_KEY, product_key);
        dbo.put(USER_ID, user_id);
        dbo.put(URL, url);
        dbo.put(APPROVED, approved);
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        return dbo;
    }

    public void updateFromDbObject(DBObject object){
        setProduct_key((String)object.get(PROD_KEY));
        setUrl((String)object.get(URL));
        setUser_id((Integer)object.get(USER_ID));
        setApproved((Boolean) object.get(APPROVED));
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
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