package com.versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.versioneye.domain.MavenRepository;
import com.versioneye.persistence.IMavenRepostoryDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/11/13
 * Time: 4:02 PM
 */
public class MavenRepositoryDao implements IMavenRepostoryDao {

    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(MavenRepository.MAVENREPOSITORIES);
    }

    public void create(MavenRepository repository) {
        DBCollection mavenRepos = getCollection();
        mavenRepos.insert(repository.getDBObject());
    }

    public void remove(MavenRepository repository) {
        DBCollection mavenRepos = getCollection();
        mavenRepos.remove(repository.getDBObject());
    }

    public List<MavenRepository> loadAll() {
        List<MavenRepository> response = new ArrayList<MavenRepository>();
        DBCursor cursor = getCollection().find();
        while (cursor.hasNext()) {
            DBObject object = cursor.next();
            MavenRepository repository = new MavenRepository();
            repository.updateFromDbObject(object);
            response.add(repository);
        }
        return response;
    }

    public MavenRepository findByName(String name){
        MavenRepository repository = null;
        DBCollection mavenRepos = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(MavenRepository.NAME, name);
        DBCursor cursor = mavenRepos.find(match);
        if (cursor.hasNext()){
            DBObject productDB = cursor.next();
            repository = new MavenRepository();
            repository.updateFromDbObject(productDB);
        }
        return repository;
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
