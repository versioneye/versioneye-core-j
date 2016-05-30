package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 12/30/12
 * Time: 8:01 PM
 */
public class ProductResource {

    public static final String ID = "_id";
    public static final String URL = "url";
    public static final String NAME = "name";
    public static final String RESOURCE_TYPE = "resource_type";
    public static final String CRAWLER_NAME = "crawler_name";
    public static final String CRAWLED = "crawled";
    public static final String PROD_KEY = "prod_key";
    public static final String LANGUAGE = "language";
    // Dates
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String id;
    private String url;
    private String name;
    private String resourceType;
    private String crawlerName;
    private String prodKey;
    private String language;
    private boolean crawled;
    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(URL, url);
        doc.put(NAME, name);
        doc.put(LANGUAGE, language);
        doc.put(RESOURCE_TYPE, resourceType);
        doc.put(CRAWLER_NAME, crawlerName);
        doc.put(CRAWLED, crawled);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( object.get(ProductResource.ID).toString() );
        setUrl( (object.get(URL)).toString() );
        setName((String)object.get(NAME));
        setLanguage((String)object.get(LANGUAGE));
        setResourceType((String)object.get(RESOURCE_TYPE));
        setCrawlerName((String)object.get(CRAWLER_NAME));
        setCrawled((Boolean)object.get(CRAWLED));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProdKey() {
        return prodKey;
    }

    public void setProdKey(String prodKey) {
        this.prodKey = prodKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getCrawlerName() {
        return crawlerName;
    }

    public void setCrawlerName(String crawlerName) {
        this.crawlerName = crawlerName;
    }

    public boolean isCrawled() {
        return crawled;
    }

    public void setCrawled(boolean crawled) {
        this.crawled = crawled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
