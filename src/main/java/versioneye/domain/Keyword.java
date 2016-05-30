package versioneye.domain;

import com.mongodb.BasicDBObject;
import org.bson.types.ObjectId;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 12/29/12
 * Time: 5:18 PM
 */
public class Keyword {

    public static final String UID = "uid";
    public static final String KEYWORD = "keyword";
    public static final String CREATED_AT = "created_at";
    public static final String UPDATED_AT = "updated_at";
    public static final String KEYWORDS = "keywords";

    private String product_key;
    private Date createdAt = new Date();
    private Date updatedAt = new Date();
    private String keyword;

    public BasicDBObject getDBObject(){
        BasicDBObject dbo = new BasicDBObject();
        dbo.put(UID, new ObjectId());
        dbo.put(CREATED_AT, createdAt);
        dbo.put(UPDATED_AT, updatedAt);
        dbo.put(KEYWORD, keyword.toLowerCase());
        return dbo;
    }

    public String getProduct_key() {
        return product_key;
    }

    public void setProduct_key(String product_key) {
        this.product_key = product_key;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
