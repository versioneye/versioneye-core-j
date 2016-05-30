package com.versioneye.persistence;

import com.versioneye.domain.ProductResource;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 12/30/12
 * Time: 8:13 PM
 */
public interface IProductResourceDao {

    List<ProductResource> getGitHubResources();

    void updateCrawledForGithub(ProductResource resource);

}
