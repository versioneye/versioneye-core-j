package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.Dependency;
import com.versioneye.persistence.IDependencyDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 5/17/12
 * Time: 11:32 PM
 */
public class DependencyDao implements IDependencyDao {

    private static final String DEPENDENCIES = "dependencies";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(DEPENDENCIES);
    }

    public void create(Dependency dependency) {
        DBCollection dependencies = getCollection();
        dependencies.insert(dependency.getDBObject());
    }

    public List<Dependency> find(String language, String prodKey, String version) {
        List<Dependency> deps = new ArrayList<Dependency>();
        BasicDBObject match = new BasicDBObject();
        match.put(Dependency.LANGUAGE, language);
        match.put(Dependency.PROD_KEY, prodKey);
        match.put(Dependency.PROD_VERSION, version);
        DBCollection dependencies = getCollection();
        DBCursor cursor = dependencies.find(match);
        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            Dependency dependency = new Dependency(null, null, null, null, null, null);
            dependency.updateFromDbObject(dbObject);
            deps.add(dependency);
        }
        return deps;
    }

    public boolean existAlready(String language, String prodKey, String prod_version, String depProdKey, String version) {
        BasicDBObject match = new BasicDBObject();
        match.put(Dependency.LANGUAGE, language);
        match.put(Dependency.PROD_KEY, prodKey);
        match.put(Dependency.PROD_VERSION, prod_version);
        match.put(Dependency.DEP_PROD_KEY, depProdKey);
        match.put(Dependency.VERSION, version);
        DBCollection dependencies = getCollection();
        DBCursor cursor = dependencies.find(match);
        return cursor.hasNext();
    }

    public boolean deleteDependencies(String language, String prodKey, String prod_version) {
        BasicDBObject match = new BasicDBObject();
        match.put(Dependency.LANGUAGE, language);
        match.put(Dependency.PROD_KEY, prodKey);
        match.put(Dependency.PROD_VERSION, prod_version);
        DBCollection dependencies = getCollection();
        DBCursor cursor = dependencies.find(match);
        while (cursor.hasNext()){
            DBObject dbObject = cursor.next();
            dependencies.remove(dbObject);
        }
        return cursor.hasNext();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}