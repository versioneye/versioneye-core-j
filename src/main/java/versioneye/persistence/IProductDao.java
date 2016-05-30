package versioneye.persistence;

import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import versioneye.domain.Keyword;
import versioneye.domain.Product;
import versioneye.domain.Repository;
import versioneye.domain.Version;

import java.util.List;
import java.util.Set;

public interface IProductDao {

    void dropAllProducts();

    List<ObjectId> getUniqueFollowedJavaIds();

    void updateDescription(String language, String prodKey, String desciption);

    Product create(final Product product);

    void create(DBObject product);

    boolean existAlready(String language, String key);

    boolean doesVersionExistAlready(String language, String productKey, String version);
    boolean doesVersionExistAlreadyByGA(String groupId, String artifactId, String version);

    boolean doesPomUrlExistAlready(String url);

    void addNewVersion(String language, String prodKey, Version version);

    void addNewUser(ObjectId userId, String language, String prod_key);

    void updateVersionReleaseTime(Version version);

    boolean doesRepositoryExistAlready(String language, String productKey, String repositorySrc);

    void addNewRepository(String language, String prod_key, Repository repository);

    boolean doesKeywordExistAlready(String language, String productKey, String keyword);
    void addKeyword(String language, String prodKey, Keyword keyword);

    Product getByKey(String language, String key) throws Exception;
    Product getByKey(String language, String groupId, String artifactId) throws Exception;
    Product getByGA(String groupId, String artifactId) throws Exception;

    Product getById(String id) throws Exception;

    Set<Product> getByIds(ObjectId[] ids) throws Exception;

    List<Product> getAllNpmProducts();

    List<Product> fetchProductsFromRepo(String language, String repoSrc);

    List<Product> fetchProductsWithEmptyReleaseString(String language);

    void remove(DBObject object);

    void drop();

}
