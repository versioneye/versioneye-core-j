package versioneye.domain;

import com.mongodb.BasicDBObject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 12/28/11
 * Time: 10:40 AM
 */
public class Repository {

    public static final String REPOSITORIES = "repositories";
    public static final String NAME = "name";
    public static final String SRC = "src";
    public static final String REPOTYPE = "repotype";
    public static final String LANGUAGE = "language";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String name;
    private String src;
    private String repoType;
    private String username;
    private String password;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String followType = "href";
    private String language = "Java";
    private boolean replaceWithRepoSrc = true;

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(NAME, name);
        dbo.put(SRC, src);
        dbo.put(REPOTYPE, repoType);
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        return dbo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Repository that = (Repository) o;
        if (src != null ? !src.equals(that.src) : that.src != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return src != null ? src.hashCode() : 0;
    }

    public String getFollowType() {
        return followType;
    }

    public void setFollowType(String followType) {
        this.followType = followType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getRepoType() {
        return repoType;
    }

    public void setRepoType(String repoType) {
        this.repoType = repoType;
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

    public Timestamp getChangedAtTimeStamp() {
        return  new Timestamp(updatedAt.getTime());
    }

    public Timestamp getCreatedAtTimeStamp() {
        return new Timestamp(createdAt.getTime());
    }

    public boolean isReplaceWithRepoSrc() {
        return replaceWithRepoSrc;
    }

    public void setReplaceWithRepoSrc(boolean replaceWithRepoSrc) {
        this.replaceWithRepoSrc = replaceWithRepoSrc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}