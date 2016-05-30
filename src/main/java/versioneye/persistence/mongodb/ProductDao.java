package versioneye.persistence.mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;
import versioneye.domain.Keyword;
import versioneye.domain.Product;
import versioneye.domain.Repository;
import versioneye.domain.Version;
import versioneye.persistence.IProductDao;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 1/1/12
 * Time: 6:53 PM
 */
public class ProductDao implements IProductDao {

    private static final String PRODUCTS = "products";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(PRODUCTS);
    }

    public void dropAllProducts(){
        DBCollection products = getCollection();
        products.drop();
    }

    public List<ObjectId> getUniqueFollowedJavaIds(){
        List<ObjectId> result = new ArrayList<ObjectId>();
        BasicDBObject query = new BasicDBObject();
        query.put("followers", new BasicDBObject("$gt", 0)) ;
        query.put("language", "Java");
        DBCollection products = getCollection();
        DBCursor cursor = products.find(query);
        while (cursor.hasNext()){
            DBObject productDB = cursor.next();
            result.add( (ObjectId) productDB.get(Product.ID) );
        }
        return result;
    }

    public void updateDescription(String language, String prodKey, String desciption){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, prodKey);

        BasicDBObject newValues = new BasicDBObject();
        newValues.put(Product.DESCRIPTION, desciption);
        newValues.put(Product.REINDEX, true);
        newValues.put(Product.UPDATED_AT, new Date());

        BasicDBObject set = new BasicDBObject("$set", newValues);

        getCollection().update(match, set);
    }

    public Product create(Product product) {
        DBCollection products = getCollection();
        products.insert(product.getDBObject());
        if (product.getRepositories() != null)
            for (String key : product.getRepositories().keySet()){
                Repository repository = product.getRepositories().get(key);
                addNewRepository(product.getLanguage(), product.getProd_key(), repository);
            }
        return product;
    }

    public void create(DBObject product) {
        DBCollection products = getCollection();
        products.insert(product);
    }

    public boolean existAlready(String language, String key) {
        DBCollection products = getCollection();
        BasicDBObject doc = new BasicDBObject();
        doc.put(Product.LANGUAGE, language);
        doc.put(Product.PROD_KEY, key);
        DBCursor cursor = products.find(doc);
        return cursor.hasNext();
    }

    public Product getByKey(String language, String key) throws Exception{
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, key.toLowerCase());
        DBCursor cursor = products.find(match);
        if (cursor.size() > 1)
            throw new Exception("To Much!");
        if (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
        }
        return product;
    }

    public Product getByKey(String language, String groupId, String artifactId) throws Exception {
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.GROUPID, groupId);
        match.put(Product.ARTIFACTID, artifactId);
        DBCursor cursor = products.find(match);
        if (cursor.size() > 1)
            throw new Exception("To Much!");
        if (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
        }
        return product;
    }

    public Product getByGA(String groupId, String artifactId) throws Exception {
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.GROUPID, groupId);
        match.put(Product.ARTIFACTID, artifactId);
        DBCursor cursor = products.find(match);
        if (cursor.size() > 1)
            throw new Exception("To Much!");
        if (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
        }
        return product;
    }

    public Set<Product> getByIds(ObjectId[] ids) throws Exception {
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject in = new BasicDBObject();
        in.put("$in", ids);
        BasicDBObject match = new BasicDBObject();
        match.put(Product.ID, in);
        DBCursor cursor = products.find(match);
        Set<Product> results = new HashSet<Product>();
        while (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
            results.add(product);
        }
        return results;
    }

    public Product getById(String id) throws Exception {
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.ID, new ObjectId(id));
        DBCursor cursor = products.find(match);
        if (cursor.size() > 1)
            throw new Exception("To Much!");
        if (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
        }
        return product;
    }

    public List<Product> getAllNpmProducts() {
        Product product = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, "Node.JS");
        DBCursor cursor = products.find(match);
        List<Product> npms = new ArrayList<Product>();
        while (cursor.hasNext()){
            DBObject productDB = cursor.next();
            product = new Product();
            product.updateFromDbObject(productDB);
            npms.add(product);
        }
        return npms;
    }

    public DBObject getDBObjectByKey(String language, String key) {
        DBObject productDB = null;
        DBCollection products = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, key);
        DBCursor cursor = products.find(match);
        if (cursor.hasNext())
            productDB = cursor.next();
        return productDB;
    }

    public boolean doesVersionExistAlready(String language, String productKey, String version){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, productKey);
        match.put("versions.version", version);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean doesVersionExistAlreadyByGA(String groupId, String artifactId, String version){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.GROUPID, groupId);
        match.put(Product.ARTIFACTID, artifactId);
        match.put("versions.version", version);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public List<Product> fetchProductsFromRepo(String language, String repoSrc){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put("repositories.src", repoSrc);
        DBCursor cur = getCollection().find(match);
        List<Product> products = new ArrayList<Product>();
        while (cur.hasNext()){
            DBObject productDB = cur.next();
            Product product = new Product();
            product.updateFromDbObject(productDB);
            products.add(product);
        }
        return products;
    }

    public List<Product> fetchProductsWithEmptyReleaseString(String language){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put("versions.released_string", null);
        DBCursor cur = getCollection().find(match);
        List<Product> products = new ArrayList<Product>();
        while (cur.hasNext()){
            DBObject productDB = cur.next();
            Product product = new Product();
            product.updateFromDbObject(productDB);
            products.add(product);
        }
        return products;
    }

    public void addNewVersion(String language, String prodKey, Version version){
        BasicDBObject productMatch = new BasicDBObject();
        productMatch.put(Product.LANGUAGE, language);
        productMatch.put(Product.PROD_KEY, prodKey);

        BasicDBObject versionObj = version.getDBObject();
        versionObj.put(Version.VERSION, version.getVersion());
        BasicDBObject versionsUpdate = new BasicDBObject();
        versionsUpdate.put("$push", new BasicDBObject(Version.VERSIONS, versionObj));
        getCollection().update(productMatch, versionsUpdate, true, true, WriteConcern.FSYNC_SAFE);

        BasicDBObject newValues = new BasicDBObject();
        newValues.put(Product.REINDEX, true);
        newValues.put(Product.UPDATED_AT, new Date());
        BasicDBObject set = new BasicDBObject("$set", newValues);
        getCollection().update(productMatch, set, false, false, WriteConcern.FSYNC_SAFE);
    }

    // TODO test
    public void addNewUser(ObjectId userId, String language, String prod_key){
        BasicDBObject productMatch = new BasicDBObject();
        productMatch.put(Product.LANGUAGE, language);
        productMatch.put(Product.PROD_KEY, prod_key);

        BasicDBObject newUserId = new BasicDBObject();
        newUserId.put("$push", new BasicDBObject(Product.USER_IDS, userId));

        getCollection().update(productMatch, newUserId, true, true, WriteConcern.MAJORITY);
        try{
            Product product = getByKey(language, prod_key);
            int count = product.getUser_ids().size();
            BasicDBObject newFollowers = new BasicDBObject();
            newFollowers.put("followers", count);
            BasicDBObject set = new BasicDBObject("$set", newFollowers);
            getCollection().update(productMatch, set);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void updateVersionReleaseTime(Version version){
        if (version.getProduct_key() == null || version.getVersion() == null || version.getReleased_string() == null){
            return ;
        }
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, version.getLanguage());
        match.put(Product.PROD_KEY, version.getProduct_key());
        match.put("versions.version", version.getVersion());
        BasicDBObject newValues = new BasicDBObject();
        newValues.put("versions.$.released_string", version.getReleased_string());
        newValues.put("versions.$.released_at", version.getReleased_at());
        BasicDBObject set = new BasicDBObject("$set", newValues);
        getCollection().update(match, set);
    }

    public boolean doesKeywordExistAlready(String language, String productKey, String keyword){
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, productKey);
        match.put("keywords.keyword", keyword.toLowerCase());
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public void addKeyword(String language, String prodKey, Keyword keyword){
        BasicDBObject productMatch = new BasicDBObject();
        productMatch.put(Product.LANGUAGE, language);
        productMatch.put(Product.PROD_KEY, prodKey);

        BasicDBObject keywordObj = keyword.getDBObject();

        BasicDBObject keywordUpdate = new BasicDBObject();
        keywordUpdate.put("$addToSet", new BasicDBObject(Keyword.KEYWORDS, keywordObj));

        getCollection().update(productMatch, keywordUpdate);
    }

    public boolean doesRepositoryExistAlready(String language, String productKey, String repositorySrc) {
        BasicDBObject match = new BasicDBObject();
        match.put(Product.LANGUAGE, language);
        match.put(Product.PROD_KEY, productKey);
        match.put("repositories.src", repositorySrc);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public boolean doesPomUrlExistAlready(String url) {
        BasicDBObject match = new BasicDBObject();
        match.put("versions.pom", url);
        DBCursor cur = getCollection().find(match);
        return cur.hasNext();
    }

    public void addNewRepository(String language, String prod_key, Repository repository) {
        BasicDBObject productMatch = new BasicDBObject();
        productMatch.put(Product.LANGUAGE, language);
        productMatch.put(Product.PROD_KEY, prod_key);
        BasicDBObject repoObj = repository.getDBObject();
        BasicDBObject repoUpdate = new BasicDBObject();
        repoUpdate.put("$addToSet", new BasicDBObject(Repository.REPOSITORIES, repoObj));
        getCollection().update(productMatch, repoUpdate);
    }

    public void remove(DBObject object){
        getCollection().remove(object);
    }

    public void drop(){
        getCollection().drop();
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}
