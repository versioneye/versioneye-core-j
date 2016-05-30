package versioneye.domain;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;
import java.util.HashMap;

public class Version {

    public static final String UID = "uid";
    public static final String VERSION = "version";
    public static final String LINK = "link";
    public static final String DOWNLOADS = "downloads";
    public static final String POM = "pom";
    public static final String AUTHORS = "authors";
    public static final String DESCRIPTION = "description";
    public static final String SUMMARY = "summary";
    public static final String PRERELEASE = "prerelease";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String VERSIONS = "versions";
    public static final String RELEASED_STRING = "released_string";
    public static final String RELEASED_AT = "released_at";

    private String version;
    private String link;
    private String pom;
    private String language;
    private String product_key;
    private Integer downloads;
    private String authors;
    private String description;
    private String summary;
    private String released_string;
    private Date released_at;
    private boolean prerelease;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String type;

    private HashMap<String, Versionlink> links;
    private HashMap<String, Versionarchive> archives;

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(UID, new ObjectId());
        dbo.put(VERSION, version);
        dbo.put(LINK, link);
        dbo.put(POM, pom);
        dbo.put(DOWNLOADS, downloads);
        dbo.put(AUTHORS, authors);
        dbo.put(DESCRIPTION, description);
        dbo.put(SUMMARY, summary);
        dbo.put(PRERELEASE, prerelease);
        dbo.put(RELEASED_STRING, released_string);
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        dbo.put(RELEASED_AT, released_at);
        return dbo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Version version1 = (Version) o;
        if (version != null ? !version.equals(version1.version) : version1.version != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return version != null ? version.hashCode() : 0;
    }

    public String getVersionUrl(){
        String urlKey = new String(version);
        urlKey = urlKey.replaceAll("/", "--");
        urlKey = urlKey.replaceAll("\\.", "~");
        return urlKey;
    }

    public String getProdKeyUrl(){
        String urlKey = new String(product_key);
        urlKey = urlKey.replaceAll("/", "--");
        urlKey = urlKey.replaceAll("\\.", "~");
        return urlKey;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getReleased_string() {
        return released_string;
    }

    public void setReleased_string(String released_string) {
        this.released_string = released_string;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public boolean isPrerelease() {
        return prerelease;
    }

    public void setPrerelease(boolean prerelease) {
        this.prerelease = prerelease;
    }

    public Integer getDownloads() {
        return downloads;
    }

    public void setDownloads(Integer downloads) {
        this.downloads = downloads;
    }

    public String getPom() {
        return pom;
    }

    public void setPom(String pom) {
        this.pom = pom;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
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

    public Date getReleased_at() {
        return released_at;
    }

    public void setReleased_at(Date released_at) {
        this.released_at = released_at;
    }

    public HashMap<String, Versionlink> getLinks() {
        return links;
    }

    public void setLinks(HashMap<String, Versionlink> links) {
        this.links = links;
    }

    public HashMap<String, Versionarchive> getArchives() {
        return archives;
    }

    public void setArchives(HashMap<String, Versionarchive> archives) {
        this.archives = archives;
    }
}