package versioneye.domain;

import com.mongodb.BasicDBObject;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 12:56 PM
 */
public class Versionlink {

    public static final String LANGUAGE = "language";
    public static final String PROD_KEY = "prod_key";
    public static final String VERSION_ID = "version_id";
    public static final String LINK = "link";
    public static final String NAME = "name";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String language;
    private String link;
    private String name;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String product_key;
    private String version_id;

    public Versionlink( String language, String product_key, String name, String link ){
        this.language = language;
        this.product_key = product_key;
        this.name = name;
        this.link = link;
    }

    public Versionlink( String language, String product_key, String name, String link, String version ){
        this.language = language;
        this.product_key = product_key;
        this.name = name;
        this.link = link;
        this.version_id = version;
    }

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(LANGUAGE, language);
        dbo.put(PROD_KEY, product_key.toLowerCase());
        dbo.put(VERSION_ID, version_id);
        dbo.put(NAME, name);
        dbo.put(LINK, link);
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        return dbo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Versionlink that = (Versionlink) o;
        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return link != null ? link.hashCode() : 0;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getVersion_id() {
        return version_id;
    }

    public void setVersion_id(String version_id) {
        this.version_id = version_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
