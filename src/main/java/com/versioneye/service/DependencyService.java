package com.versioneye.service;

import org.htmlcleaner.TagNode;
import com.versioneye.domain.Dependency;
import com.versioneye.domain.Product;
import com.versioneye.persistence.IDependencyDao;
import com.versioneye.persistence.IProductDao;
import com.versioneye.utils.HttpUtils;
import com.versioneye.utils.LogUtils;

import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 6:19 PM
 */
public class DependencyService {

    private static final String SLASH = "/";
    private IDependencyDao dependencyDao;
    private IProductDao productDao;
    private LogUtils logUtils;
    private HttpUtils httpUtils;

    // This is the old way, if there is no maven index available !
    public void createDependenciesIfNotExist( TagNode pom, Product product, HashMap<String, String> properties){
        try{
            Object[] deps = pom.evaluateXPath("//project/dependencies/dependency");
            for (Object dep: deps){
                if ( (dep instanceof TagNode) == false )
                    continue;
                TagNode depend    = (TagNode) dep;

                String groupId    = httpUtils.getSingleValue(depend.evaluateXPath("/groupId"),    properties);
                String artifactId = httpUtils.getSingleValue(depend.evaluateXPath("/artifactId"), properties);

                if (groupId == null || groupId.trim().equals("") || artifactId == null || artifactId.trim().equals(""))
                    continue;

                String version    = httpUtils.getSingleValue(depend.evaluateXPath("/version"),    properties);
                String scope      = httpUtils.getSingleValue(depend.evaluateXPath("/scope"),      properties);
                if (scope == null)
                    scope = "compile";

                Dependency dependency = buildDependency(groupId, artifactId, version, scope, product);
                createDependencyIfNotExist(dependency);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logUtils.addError("Error in createDependenciesIfNotExist( TagNode ...", ex.getMessage(), null);
        }
    }



    public void createDependencyIfNotExist(Dependency dependency){
        updateKnownStatus(dependency);
        boolean existAlready = dependencyDao.existAlready(dependency.getLanguage(), dependency.getProdKey(),
                dependency.getProdVersion(), dependency.getDepProdKey(), dependency.getVersion());
        if (!existAlready){
            dependencyDao.create(dependency);
        }
    }

    public void updateKnownStatus(Dependency dependency){
        if (dependency.getLanguage() == null){
            dependency.setKnown(Boolean.FALSE);
            return ;
        }
        try{
            if (productDao.existAlready(dependency.getLanguage(), dependency.getDepProdKey())){
                dependency.setKnown(Boolean.TRUE);
            } else if (dependency.getLanguage().equals("Clojure") && productDao.existAlready("Java", dependency.getDepProdKey())){
                dependency.setLanguage("Java");
                dependency.setKnown(Boolean.TRUE);
            } else {
                dependency.setKnown(Boolean.FALSE);
            }
        } catch (Exception exception){
            logUtils.addError("error in DependencyService.updateKnownStatus", exception.toString(), null);
        }
    }

    public Dependency buildDependency(String groupId, String artifactId, String version, String scope, Product product){
        String key = groupId + SLASH + artifactId;
        Dependency dependency = new Dependency(product.getLanguage(), product.getProd_key(),
                product.getVersion(), artifactId, version, key );
        dependency.setProdType(product.getProd_type());
        dependency.setGroupId(groupId);       // GroupId    of the dependency
        dependency.setArtifactId(artifactId); // ArtifactId of the dependency
        dependency.setScope(scope);           // Scope      of the dependency
        return dependency;
    }

    public void setDependencyDao(IDependencyDao dependencyDao) {
        this.dependencyDao = dependencyDao;
    }

    public void setLogUtils(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

    public void setProductDao(IProductDao productDao) {
        this.productDao = productDao;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

}
