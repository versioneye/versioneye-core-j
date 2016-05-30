package com.versioneye.service;

import com.versioneye.domain.License;
import com.versioneye.domain.Product;
import com.versioneye.persistence.ILicenseDao;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 8/19/13
 * Time: 7:14 PM
 */
public class LicenseService {

    private ILicenseDao licenseDao;

    public void createLicenseIfNotExist(Product product, String name, String url, String comments, String distributions){
        if (name == null || name.equals("") || name.equalsIgnoreCase("UNKNOWN"))
            return ;

        if (licenseDao.existAlready(product.getLanguage(), product.getProd_key(), product.getVersion(), name, url))
            return ;

        License license = new License();

        license.setLanguage(product.getLanguage());
        license.setProd_key(product.getProd_key());
        license.setVersion(product.getVersion());

        license.setName(name);
        license.setUrl(url);
        license.setComments(comments);
        license.setDistributions(distributions);

        licenseDao.create(license);
    }

    public void setLicenseDao(ILicenseDao licenseDao) {
        this.licenseDao = licenseDao;
    }

}
