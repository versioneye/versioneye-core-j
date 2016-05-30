package versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.Date;

public class Crawle {

    public static final String CRAWLES = "crawles";
    public static final String ID = "_id";
    public static final String CRAWLER_NAME = "crawler_name";
    public static final String CRAWLER_VERSION = "crawler_version";
    public static final String REPOSITORY_SRC = "repository_src";
    public static final String START_POINT = "start_point";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String DURATION = "duration";
    public static final String EXECGROUP = "exec_group";

    private ObjectId id;
    private String crawlerName;
    private String crawlerVersion;
    private String repositorySrc;
    private String startPoint;
    private Date createdAt = new Date();
    private Date updatedAt;
    private Date duration;
    private String execGroup;

    public Crawle(){
        id = new ObjectId();
    }

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(ID, id);
        doc.put(CRAWLER_NAME, crawlerName);
        doc.put(CRAWLER_VERSION, crawlerVersion);
        doc.put(START_POINT, startPoint);
        doc.put(EXECGROUP, execGroup);
        doc.put(REPOSITORY_SRC, repositorySrc);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, updatedAt);
        doc.put(DURATION, duration);
        return doc;
    }

    public void updateFromDBObject(DBObject object){
        id = (ObjectId) object.get(ID);
        crawlerName = (String) object.get(CRAWLER_NAME);
        crawlerVersion = (String) object.get(CRAWLER_VERSION);
        execGroup = (String) object.get(EXECGROUP);
        startPoint = (String) object.get(START_POINT);
        repositorySrc = (String) object.get(REPOSITORY_SRC);
        createdAt = (Date) object.get(CREATED_AT);
        updatedAt = (Date) object.get(UPDATED_AT);
        duration = (Date) object.get(DURATION);
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCrawlerName() {
        return crawlerName;
    }

    public void setCrawlerName(String crawlerName) {
        this.crawlerName = crawlerName;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public String getCrawlerVersion() {
        return crawlerVersion;
    }

    public void setCrawlerVersion(String crawlerVersion) {
        this.crawlerVersion = crawlerVersion;
    }

    public String getRepositorySrc() {
        return repositorySrc;
    }

    public void setRepositorySrc(String repositorySrc) {
        this.repositorySrc = repositorySrc;
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

    public Date getDuration() {
        return duration;
    }

    public void setDuration(Date duration) {
        this.duration = duration;
    }

    public String getExecGroup() {
        return execGroup;
    }

    public void setExecGroup(String execGroup) {
        this.execGroup = execGroup;
    }
}