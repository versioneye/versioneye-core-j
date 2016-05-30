package versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/11/13
 * Time: 3:56 PM
 */
public class MavenRepository {

    public static final String MAVENREPOSITORIES = "maven_repositories";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String URL = "url";
    public static final String REPLACE_WITH_REPO_SRC = "replace_with_repo_src";
    public static final String LANGUAGE = "language";

    // Dates
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String id;
    private String name;
    private String url;
    private String username;
    private String password;
    private Boolean replaceWithRepoSrc = true;
    private String language;
    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(NAME, name);
        doc.put(URL, url);
        doc.put(REPLACE_WITH_REPO_SRC, replaceWithRepoSrc);
        doc.put(LANGUAGE, language);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (object.get(ID)).toString() );
        setName((String) object.get(NAME));
        setUrl( (String) object.get(URL) );
        setReplaceWithRepoSrc( (Boolean) object.get(REPLACE_WITH_REPO_SRC) );
        setLanguage( (String) object.get( LANGUAGE ) );
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

    public Boolean getReplaceWithRepoSrc() {
        return replaceWithRepoSrc;
    }

    public void setReplaceWithRepoSrc(Boolean replaceWithRepoSrc) {
        this.replaceWithRepoSrc = replaceWithRepoSrc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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
