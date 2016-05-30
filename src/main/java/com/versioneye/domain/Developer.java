package com.versioneye.domain;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 6/2/12
 * Time: 8:39 AM
 *
 */
public class Developer {

    public static final String LANGUAGE = "language";
    public static final String ID = "_id";
    public static final String PROD_KEY = "prod_key";
    public static final String VERSION = "version";
    public static final String DEVELOPER_ID = "developer";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String HOMEPAGE = "homepage";
    public static final String ORGANIZATION = "organization";
    public static final String ORGANIZATION_URL = "organization_url";
    public static final String ROLE = "role";
    public static final String TIMEZONE = "timezone";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String language;
    private String prodKey;
    private String version;
    private ObjectId id;
    private String developerId;
    private String name;
    private String email;
    private String homepage;
    private String organization;
    private String organizationUrl;
    private String role;
    private String timezone;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public Developer(){}

    public Developer(String language, String prodKey, String version, String developerId, String name, String email,
                     String homepage, String role, String organization, String organizationUrl, String timezone){
        this.language = language;
        this.prodKey = prodKey;
        this.version = version;
        this.developerId = developerId;
        this.name = name;
        this.email = email;
        this.homepage = homepage;
        this.role = role;
        this.organization = organization;
        this.organizationUrl = organizationUrl;
        this.timezone = timezone;
    }

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(LANGUAGE, language);
        doc.put(PROD_KEY, prodKey.toLowerCase());
        doc.put(VERSION, version);
        doc.put(DEVELOPER_ID, developerId);
        doc.put(NAME, name);
        doc.put(EMAIL, email);
        doc.put(HOMEPAGE, homepage);
        doc.put(ROLE, role);
        doc.put(ORGANIZATION, organization);
        doc.put(ORGANIZATION_URL, organizationUrl);
        doc.put(TIMEZONE, timezone);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        return doc;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getHomepage() {
        return homepage;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getProdKey() {
        return prodKey;
    }

    public void setProdKey(String prodKey) {
        this.prodKey = prodKey;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationUrl() {
        return organizationUrl;
    }

    public void setOrganizationUrl(String organizationUrl) {
        this.organizationUrl = organizationUrl;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}