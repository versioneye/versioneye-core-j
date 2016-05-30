package versioneye.service;

import versioneye.domain.Product;
import versioneye.domain.Versionarchive;
import versioneye.persistence.IVersionarchiveDao;
import versioneye.utils.HttpUtils;
import versioneye.utils.LogUtils;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 6:05 PM
 */
public class ArchiveService {

    private IVersionarchiveDao versionarchiveDao;
    private HttpUtils httpUtils;
    private LogUtils logUtils;

    public void createArchive(Product product, String urlToArchive, String archiveName) {
        boolean exist = versionarchiveDao.doesArchiveExistArleady(product.getLanguage(), product.getProd_key(), product.getVersion(), archiveName, urlToArchive);
        if (exist){
            return ;
        }
        try {
//            int code = httpUtils.getResponseCode(urlToArchive);
//            if (code != 200){
//                return ;
//            }
            versionarchiveDao.removeArchive(product.getLanguage(), product.getProd_key(), product.getVersion(), archiveName);
            Versionarchive archive = new Versionarchive(product.getLanguage(), product.getProd_key(),
                    archiveName, urlToArchive);
            archive.setVersion_id(product.getVersion());
            versionarchiveDao.create(archive);
        } catch (Exception ex){
            logUtils.addError("error in ArchiveService.createArchive", ex.toString(), null);
            System.out.println("error in ArchiveService.createArchive for " + urlToArchive);
            ex.printStackTrace();
        }
    }

    public void createArchiveIfNotExist(Product product, Versionarchive archive) {
        boolean exist = versionarchiveDao.doesArchiveExistArleadyByName(product.getLanguage(), product.getProd_key(), archive.getVersion_id(), archive.getName());
        if (!exist){
            versionarchiveDao.create(archive);
        }
    }

    public void createArchivesIfNotExist(Product product, String urlToPom){
        String urlToJar        = urlToPom.replaceAll(".pom", ".jar");
        String urlToSourcJar   = urlToPom.replaceAll(".pom", "-sources.jar");
        String urlToJavaDocJar = urlToPom.replaceAll(".pom", "-javadoc.jar");

//        String urlToZip        = urlToPom.replaceAll(".pom", ".zip");
//        String urlToTarGz      = urlToPom.replaceAll(".pom", ".tar.gz");
//        String urlToWar        = urlToPom.replaceAll(".pom", ".war");
//        String urlToEar        = urlToPom.replaceAll(".pom", ".ear");
//        String urlToAar        = urlToPom.replaceAll(".pom", ".aar");

        String jarName        = product.getArtifactId() + "-" + product.getVersion() + ".jar";
        String jarSourceName  = product.getArtifactId() + "-" + product.getVersion() + "-sources.jar";
        String jarJavadocName = product.getArtifactId() + "-" + product.getVersion() + "-javadoc.jar";
        String pomName        = product.getArtifactId() + "-" + product.getVersion() + ".pom";
//        String zipName        = product.getArtifactId() + "-" + product.getVersion() + ".zip";
//        String tarGzName      = product.getArtifactId() + "-" + product.getVersion() + ".tar.gz";
//        String warName        = product.getArtifactId() + "-" + product.getVersion() + ".war";
//        String earName        = product.getArtifactId() + "-" + product.getVersion() + ".ear";
//        String aarName        = product.getArtifactId() + "-" + product.getVersion() + ".aar";

        createArchive(product, urlToPom,        pomName);
        createArchive(product, urlToJar,        jarName);
        createArchive(product, urlToSourcJar,   jarSourceName);
        createArchive(product, urlToJavaDocJar, jarJavadocName);
//        createArchive(product, urlToZip,        zipName);
//        createArchive(product, urlToTarGz,      tarGzName);
//        createArchive(product, urlToWar,        warName);
//        createArchive(product, urlToEar,        earName);
//        createArchive(product, urlToAar,        aarName);
    }

    public void setVersionarchiveDao(IVersionarchiveDao versionarchiveDao) {
        this.versionarchiveDao = versionarchiveDao;
    }

    public void setHttpUtils(HttpUtils httpUtils) {
        this.httpUtils = httpUtils;
    }

    public void setLogUtils(LogUtils logUtils) {
        this.logUtils = logUtils;
    }

}
