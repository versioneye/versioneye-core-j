package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 7/29/13
 * Time: 2:25 PM
 */
public class License {

    public static final String LICENSES = "licenses";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String COMMENTS = "comments";
    public static final String DISTRIBUTIONS = "distributions";

    // Belongs to this product
    public static final String LANGUAGE = "language";
    public static final String PROD_KEY = "prod_key";
    public static final String VERSION = "version";

    // Dates
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String id;
    private String name;
    private String url;
    private String comments;
    private String distributions;

    private String language;
    private String prod_key;  // GroupId / ArtifactId
    private String version;

    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(NAME, name);
        doc.put(URL, url);
        doc.put(COMMENTS, comments);
        doc.put(DISTRIBUTIONS, distributions);
        doc.put(LANGUAGE, language);
        doc.put(PROD_KEY, prod_key.toLowerCase());
        doc.put(VERSION, version);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (object.get(ID)).toString() );
        setName((String) object.get(NAME));
        setUrl( (String) object.get(URL) );
        setComments( (String) object.get(COMMENTS) );
        setDistributions( (String) object.get(DISTRIBUTIONS) );
        setLanguage( (String) object.get( LANGUAGE ) );
        setProd_key((String) object.get( PROD_KEY ));
        setVersion((String) object.get( VERSION ));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDistributions() {
        return distributions;
    }

    public void setDistributions(String distributions) {
        this.distributions = distributions;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getProd_key() {
        return prod_key;
    }

    public void setProd_key(String prod_key) {
        this.prod_key = prod_key;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
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
}
