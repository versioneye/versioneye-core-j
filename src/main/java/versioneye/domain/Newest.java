package versioneye.domain;

import com.mongodb.BasicDBObject;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 4/8/12
 * Time: 5:32 PM
 */
public class Newest {

    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String PROD_KEY = "prod_key";
    public static final String LANGUAGE = "language";
    public static final String PROD_TYPE = "prod_type";
    public static final String VERSION = "version";
    public static final String PRODUCT_ID = "product_id";
    // Dates
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String id;
    private String name;
    private String prod_type;
    private String prod_key;  // GroupId / ArtifactId
    private String language;
    private String version;
    private String product_id;
    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public Newest(String prod_key, String language, String prod_type, String name, String version){
        this.prod_key = prod_key;
        this.language = language;
        this.prod_type = prod_type;
        this.name = name;
        this.version = version;
    }

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(PROD_KEY, prod_key.toLowerCase());
        doc.put(LANGUAGE, language);
        doc.put(PROD_TYPE, prod_type);
        doc.put(NAME, name);
        doc.put(PRODUCT_ID, product_id);
        doc.put(VERSION, version);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
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

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
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

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
