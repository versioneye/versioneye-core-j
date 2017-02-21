package com.versioneye.domain;


import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Artefact {

    public static final String ARTEFACTS = "artefacts";
    public static final String ID = "_id";
    public static final String LANGUAGE = "language";
    public static final String PROD_KEY = "prod_key";
    public static final String VERSION = "version";
    public static final String GROUPID = "group_id";
    public static final String ARTIFACTID = "artifact_id";

    public static final String CLASSIFIER = "classifier";
    public static final String PACKAGING = "packaging";
    public static final String PROD_TYPE = "prod_type";
    public static final String SHA_VALUE = "sha_value";
    public static final String SHA_METHOD = "sha_method";
    public static final String FILE = "file";

    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private ObjectId id;
    private String language;
    private String prod_key;
    private String version;
    private String group_id;
    private String artifact_id;
    private String classifier;
    private String packaging;
    private String prod_type;
    private String sha_value;
    private String sha_method;
    private String file;
    private Date createdAt = new Date();
    private Date updatedAt;

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(LANGUAGE, language);
        doc.put(PROD_KEY, prod_key);
        doc.put(VERSION, version);
        doc.put(GROUPID, group_id);
        doc.put(ARTIFACTID, artifact_id);
        doc.put(CLASSIFIER, classifier);
        doc.put(PACKAGING, packaging);
        doc.put(PROD_TYPE, prod_type);
        doc.put(SHA_VALUE, sha_value);
        doc.put(SHA_METHOD, sha_method);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        doc.put(FILE, file);
        return doc;
    }

    public void updateFromDBObject(DBObject object){
        id = (ObjectId) object.get(ID);
        language = (String) object.get(LANGUAGE);
        prod_key = (String) object.get(PROD_KEY);
        version = (String) object.get(VERSION);
        group_id = (String) object.get(GROUPID);
        artifact_id = (String) object.get(ARTIFACTID);
        classifier = (String) object.get(CLASSIFIER);
        packaging = (String) object.get(PACKAGING);
        prod_type = (String) object.get(PROD_TYPE);
        sha_value = (String) object.get(SHA_VALUE);
        sha_method = (String) object.get(SHA_METHOD);
        createdAt = (Date) object.get(CREATED_AT);
        updatedAt = (Date) object.get(UPDATED_AT);
        file = (String) object.get(FILE);
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getArtifact_id() {
        return artifact_id;
    }

    public void setArtifact_id(String artifact_id) {
        this.artifact_id = artifact_id;
    }

    public String getClassifier() {
        return classifier;
    }

    public void setClassifier(String classifier) {
        this.classifier = classifier;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
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

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getSha_value() {
        return sha_value;
    }

    public void setSha_value(String sha_value) {
        this.sha_value = sha_value;
    }

    public String getSha_method() {
        return sha_method;
    }

    public void setSha_method(String sha_method) {
        this.sha_method = sha_method;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}
