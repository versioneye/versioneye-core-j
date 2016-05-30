package com.versioneye.domain;

import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Product {

    public static final String REPOSITORIES = "repositories";
    public static final String ID = "_id";
    public static final String NAME = "name";
    public static final String NAME_DOWNCASE = "name_downcase";
    public static final String PROD_KEY = "prod_key";
    public static final String PROD_TYPE = "prod_type";
    public static final String LANGUAGE = "language";
    public static final String FOLLOWERS = "followers";

    // Maven2
    public static final String GROUPID = "group_id";
    public static final String ARTIFACTID = "artifact_id";

    public static final String GROUPID_ORIG = "group_id_orig";
    public static final String ARTIFACTID_ORIG = "artifact_id_orig";

    public static final String AUTHORS = "authors";
    public static final String DESCRIPTION = "description";
    public static final String LINK = "link";
    public static final String DOWNLOADS = "downloads";

    // Current newest Version
    public static final String VERSION = "version";
    public static final String VERSION_LINK = "version_link";

    // Dates
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";

    public static final String ICON = "icon";
    public static final String REINDEX = "reindex";
    public static final String USER_IDS = "user_ids";

    private String id;
    private ObjectId productId;
    private String name;
    private String prod_type;
    private String prod_key;  // GroupId / ArtifactId
    private String language;
    private Integer followers = 0;

    private String groupId;
    private String artifactId;

    private String groupId_orig;
    private String artifactId_orig;

    private String authors;
    private String description;
    private String link;
    private Integer downloads;

    private String version;
    private String version_link;

    private Boolean reindex;

    private Date createdAt = new Date();
    private Date changedAt = new Date();

    private String icon;

    private HashMap<String, Repository> repositories;
    private HashMap<String, Version> versions;
    private List<ObjectId> user_ids = new ArrayList<ObjectId>();

    public BasicDBObject getDBObject(){
        BasicDBObject doc = new BasicDBObject();
        doc.put(PROD_KEY, prod_key.toLowerCase());
        doc.put(LANGUAGE, language);
        doc.put(PROD_TYPE, prod_type);
        doc.put(NAME, name);
        if (name != null){
            doc.put(NAME_DOWNCASE, name.toLowerCase());
        }
        doc.put(ARTIFACTID, artifactId);
        doc.put(GROUPID, groupId);
        doc.put(ARTIFACTID_ORIG, artifactId_orig);
        doc.put(GROUPID_ORIG, groupId_orig);
        doc.put(AUTHORS, authors);
        doc.put(DESCRIPTION, description);
        doc.put(FOLLOWERS, followers);
        doc.put(ICON, icon);
        doc.put(LINK, link);
        doc.put(DOWNLOADS, downloads);
        doc.put(VERSION, version);
        doc.put(VERSION_LINK, version_link);
        doc.put(REINDEX, true);
        doc.put(CREATED_AT, createdAt);
        doc.put(UPDATED_AT, changedAt);
        return doc;
    }

    public void updateFromDbObject(DBObject object){
        setId( (object.get(Product.ID)).toString() );
        setProductId((ObjectId) object.get(Product.ID));
        setLanguage((String) object.get(Product.LANGUAGE));
        setName((String) object.get(Product.NAME));
        setProd_key((String) object.get(Product.PROD_KEY));
        setProd_type((String) object.get(Product.PROD_TYPE));
        setArtifactId((String) object.get(Product.ARTIFACTID));
        setGroupId((String) object.get(Product.GROUPID));
        setArtifactId_orig((String) object.get(Product.ARTIFACTID_ORIG));
        setGroupId_orig((String) object.get(Product.GROUPID_ORIG));
        setAuthors((String) object.get(Product.AUTHORS));
        setDescription((String) object.get(Product.DESCRIPTION));
        setIcon((String) object.get(Product.ICON));
        setLink((String) object.get(Product.LINK));
        setDownloads((Integer) object.get(DOWNLOADS));
        setFollowers((Integer) object.get(FOLLOWERS));
        setVersion((String) object.get(Product.VERSION));
        setVersion_link((String) object.get(Product.VERSION_LINK));
        setReindex((Boolean) object.get(Product.REINDEX));
        BasicDBList userIdObjects = (BasicDBList) object.get(USER_IDS);
        if (userIdObjects != null && userIdObjects.size() > 0){
            for (String userId : userIdObjects.keySet()){
                ObjectId objectId = (ObjectId) userIdObjects.get(userId);
                user_ids.add(objectId);
            }
        }
        BasicDBList repos = (BasicDBList) object.get("repositories");
        if (repos != null && repos.size() > 0){
            for (String key: repos.keySet()){
                DBObject rep = (DBObject)repos.get(key);
                Repository repository = new Repository();
                repository.setName( (String)rep.get("name") );
                repository.setSrc( (String)rep.get("src") );
                repository.setCreatedAt( (Date) rep.get("created_at") );
                repository.setUpdatedAt( (Date) rep.get("updated_at") );
                addRepository( repository );
            }
        }
        BasicDBList versos = (BasicDBList) object.get("versions");
        if (versos != null && versos.size() > 0){
            for (String key: versos.keySet()){
                DBObject ver = (DBObject)versos.get(key);
                Version version = new Version();
                version.setVersion((String)ver.get("version"));
                version.setLink((String) ver.get("link"));
                version.setProduct_key((String)ver.get("prod_key"));
                version.setCreatedAt((Date) ver.get("created_at"));
                version.setUpdatedAt((Date) ver.get("updated_at"));
                version.setPom((String) ver.get("pom") );
                addVersion(version);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        if (prod_key != null ? !prod_key.equals(product.prod_key) : product.prod_key != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return prod_key != null ? prod_key.hashCode() : 0;
    }

    public String getProdKeyUrl(){
        String urlKey = new String(prod_key);
        urlKey = urlKey.replaceAll("/", "--");
        urlKey = urlKey.replaceAll("\\.", "~");
        return urlKey;
    }

    public Boolean getReindex() {
        return reindex;
    }

    public void setReindex(Boolean reindex) {
        this.reindex = reindex;
    }

    public List<ObjectId> getUser_ids() {
        return user_ids;
    }

    public ObjectId getProductId() {
        return productId;
    }

    public void setProductId(ObjectId productId) {
        this.productId = productId;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public Integer getFollowers() {
        return followers;
    }

    public void setFollowers(Integer followers) {
        this.followers = followers;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getVersion_link() {
        return version_link;
    }

    public void setVersion_link(String version_link) {
        this.version_link = version_link;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getProd_key() {
        return prod_key;
    }

    public void setProd_key(String prod_key) {
        if (prod_key != null){
            this.prod_key = prod_key.toLowerCase();
            return ;
        }
        this.prod_key = prod_key;
    }

    public String getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(String artifactId) {
        this.artifactId = artifactId;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, Repository> getRepositories() {
        return repositories;
    }

    public HashMap<String, Version> getVersions() {
        return versions;
    }

    public Version getVersion(String versionId){
        if (versions == null)
            return null;
        for (String key: versions.keySet()){
            if (key.equals(versionId)){
                Version version = versions.get(key);
                return version;
            }
        }
        return null;
    }

    public void setVersions(HashMap<String, Version> versions) {
        this.versions = versions;
    }

    public void addVersion(Version version){
        if (versions == null) {
            versions = new HashMap<String, Version>();
        }
        versions.put(version.getVersion(), version);
    }

    public void addRepository(Repository repository){
        if (repository == null)
            return ;
        if (repositories == null){
            repositories = new HashMap<String, Repository>();
        }
        repositories.put(repository.getName(), repository);
    }

    public String getProd_type() {
        return prod_type;
    }

    public void setProd_type(String prod_type) {
        this.prod_type = prod_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getArtifactId_orig() {
        return artifactId_orig;
    }

    public void setArtifactId_orig(String artifactId_orig) {
        this.artifactId_orig = artifactId_orig;
    }

    public String getGroupId_orig() {
        return groupId_orig;
    }

    public void setGroupId_orig(String groupId_orig) {
        this.groupId_orig = groupId_orig;
    }

}
