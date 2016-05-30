package versioneye.persistence.mongodb;

import com.mongodb.*;
import org.bson.types.ObjectId;
import versioneye.domain.ProductResource;
import versioneye.persistence.IProductResourceDao;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 12/30/12
 * Time: 8:14 PM
 */
public class ProductResourceDao implements IProductResourceDao {

    private static final String PRODUCTRESOUCES = "product_resources";
    private MongoDB mongoDB;

    private DBCollection getCollection(){
        return mongoDB.getDb().getCollection(PRODUCTRESOUCES);
    }

    public List<ProductResource> getGitHubResources() {
        List<ProductResource> resources = new ArrayList<ProductResource>();
        DBCollection productResources = getCollection();
        BasicDBObject match = new BasicDBObject();
        match.put(ProductResource.RESOURCE_TYPE, "GitHub");
        DBCursor cursor = productResources.find(match);
        while (cursor.hasNext()){
            DBObject productDB = cursor.next();
            ProductResource productResource = new ProductResource();
            productResource.updateFromDbObject(productDB);
            resources.add(productResource);
        }
        return resources;
    }

    public void updateCrawledForGithub(ProductResource resource){
        BasicDBObject match = new BasicDBObject();
        match.put(ProductResource.ID, new ObjectId( resource.getId() ) );
        BasicDBObject newValues = new BasicDBObject();
        newValues.put(ProductResource.CRAWLED, true);
        newValues.put(ProductResource.PROD_KEY, resource.getName());
        newValues.put(ProductResource.LANGUAGE, resource.getLanguage());
        BasicDBObject set = new BasicDBObject("$set", newValues);
        getCollection().update(match, set, false, true, WriteConcern.SAFE);
    }

    public void setMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

}