package versioneye.domain;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 5/17/12
 * Time: 4:30 PM
 *
 */
public class Dependency {

    public static final String DEPENDENCIES = "dependencies";
    public static final String SCOPE = "scope";
    public static final String SCOPE_RUNTIME = "runtime";
    public static final String SCOPE_DEVELOPMENT = "development";
    public static final String PROD_KEY = "prod_key";
    public static final String PROD_VERSION = "prod_version";
    public static final String DEP_PROD_KEY = "dep_prod_key";
    public static final String NAME = "name";
    public static final String VERSION = "version";
    public static final String PROD_TYPE = "prod_type";
    public static final String LANGUAGE = "language";
    public static final String COMPERATOR = "comperator";
    public static final String KNOWN = "known";
    // Maven2
    public static final String GROUPID = "group_id";
    public static final String ARTIFACTID = "artifact_id";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    private String prodKey;
    private String prodVersion;
    private String name;
    private String version;
    private String depProdKey;
    private String prodType;
    private String language;
    private String groupId;
    private String artifactId;
    private String scope;
    private String comperator;
    private Boolean known = null;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();

    public Dependency(String language, String prodKey, String prodVersion,
                      String name, String version, String depProdKey ){
        this.language = language;
        this.prodKey = prodKey;
        this.prodVersion = prodVersion;
        this.name = name;
        this.version = version;
        this.depProdKey = depProdKey;
    }

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(LANGUAGE, language);
        dbo.put(PROD_KEY, prodKey.toLowerCase());
        dbo.put(PROD_VERSION, prodVersion);

        dbo.put(GROUPID, groupId);
        dbo.put(ARTIFACTID, artifactId);
        dbo.put(NAME, name);
        dbo.put(VERSION, version);
        dbo.put(DEP_PROD_KEY, depProdKey.toLowerCase());
        dbo.put(SCOPE, scope);
        dbo.put(PROD_TYPE, prodType);
        dbo.put(COMPERATOR, comperator);
        dbo.put(KNOWN, known);
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        return dbo;
    }

    public void updateFromDbObject(DBObject object){
        setLanguage((String) object.get(LANGUAGE) );
        setProdKey((String) object.get(PROD_KEY));
        setProdVersion((String) object.get(PROD_VERSION));
        setName( (String) object.get(NAME) );
        setVersion((String) object.get(VERSION));
        setDepProdKey((String) object.get(DEP_PROD_KEY));
        setScope( (String) object.get( SCOPE) );
        setCreatedAt((Date) object.get( CREATED_AT ));
        setUpdatedAt((Date) object.get( UPDATED_AT ));
        setComperator((String) object.get( COMPERATOR ));
        setKnown((Boolean) object.get(KNOWN));
    }

    public Boolean getKnown() {
        return known;
    }

    public void setKnown(Boolean known) {
        this.known = known;
    }

    public String getComperator() {
        return comperator;
    }

    public void setComperator(String comperator) {
        this.comperator = comperator;
    }

    public String getProdKey() {
        return prodKey;
    }

    public void setProdKey(String prodKey) {
        this.prodKey = prodKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getProdVersion() {
        return prodVersion;
    }

    public void setProdVersion(String prodVersion) {
        this.prodVersion = prodVersion;
    }

    public String getDepProdKey() {
        return depProdKey;
    }

    public void setDepProdKey(String depProdKey) {
        this.depProdKey = depProdKey;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getProdType() {
        return prodType;
    }

    public void setProdType(String prodType) {
        this.prodType = prodType;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }
}