package versioneye.persistence.mongodb;


import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import versioneye.domain.Pom;
import versioneye.persistence.IPomDao;

public class PomDao implements IPomDao{

    private static final String POMS = "poms";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(POMS);
    }

    public void create(String url) {
        BasicDBObject doc = new BasicDBObject();
        doc.put(Pom.URL, url);
        getCollection().insert(doc);
    }

    public boolean existsAlready(String url) {
        DBCollection poms = getCollection();
        BasicDBObject doc = new BasicDBObject();
        doc.put(Pom.URL, url);
        DBCursor cursor = poms.find(doc);
        return cursor.hasNext();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }
}
