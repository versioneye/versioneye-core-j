package versioneye.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 12/28/11
 * Time: 10:45 AM
 */
public class RepoType {

    private Long id;
    private String name;
    private Date createdAt = new Date();
    private Date changedAt = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public Timestamp getChangedAtTimeStamp() {
        return  new Timestamp(changedAt.getTime());
    }

    public Timestamp getCreatedAtTimeStamp() {
        return new Timestamp(createdAt.getTime());
    }
}