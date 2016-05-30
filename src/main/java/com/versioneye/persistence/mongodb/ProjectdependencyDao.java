package com.versioneye.persistence.mongodb;

import com.mongodb.DBCollection;
import com.versioneye.domain.Projectdependency;
import com.versioneye.persistence.IProjectdependencyDao;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 9/5/12
 * Time: 11:40 AM
 */
public class ProjectdependencyDao implements IProjectdependencyDao {

    private static final String PROJECTDEPENDENCY = "projectdependencies";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(PROJECTDEPENDENCY);
    }

    public List<String> getUniqProdKeys() {
        DBCollection projectdependencies = getCollection();
        return projectdependencies.distinct(Projectdependency.PROD_KEY);
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
