package versioneye.persistence.mongodb;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import versioneye.domain.Versionlink;
import versioneye.persistence.IVersionlinkDao;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 2/8/12
 * Time: 1:47 PM
 */
public class VersionlinkDao implements IVersionlinkDao {

    private static final String VERSIONLINK = "versionlinks";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(VERSIONLINK);
    }

    public Versionlink create(Versionlink versionlink) {
        DBCollection versionlinks = getCollection();
        versionlinks.insert(versionlink.getDBObject());
        return versionlink;
    }

    public boolean doesLinkExistArleady(String language, String prod_key, String src){
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.LINK, src);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean doesLinkExistArleady(String language, String prod_key, String version, String src, String name) {
        BasicDBObject match = new BasicDBObject();
        match.put(Versionlink.LANGUAGE, language);
        match.put(Versionlink.PROD_KEY, prod_key);
        match.put(Versionlink.VERSION_ID, version);
        match.put(Versionlink.LINK, src);
        match.put(Versionlink.NAME, name);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}