package versioneye.utils;

import versioneye.domain.Crawle;
import versioneye.persistence.ICrawleDao;
import versioneye.persistence.IErrorDao;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 3:39 PM
 */
public class LogUtils {

    protected IErrorDao errorDao;
    protected ICrawleDao crawleDao;

    public void logStart(Date start, String crawlerName, String src){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        System.out.println("Start crawling with crawler " + crawlerName + " - Repository: " + src + " - " + sdf.format(start));
    }

    public void logStop(Date start, String name, String src){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd HH:mm");
        SimpleDateFormat sdfHour = new SimpleDateFormat("HH:mm");
        Date stop = new Date();
        Date duration = new Date(stop.getTime() - start.getTime());
        System.out.println("Stop crawler " + name + " - Repository: " + src + " - " + sdf.format(stop) + " Duration - " + sdfHour.format(duration));
        System.out.println("-");
        System.out.println("-");
        System.out.println("-");
    }

    public void updateCrawle(Crawle crawle){
        Date stop = new Date();
        Date duration = new Date(stop.getTime() - crawle.getCreatedAt().getTime());
        Timestamp updated = new Timestamp(stop.getTime());
        Timestamp durationStamp = new Timestamp(duration.getTime());
        crawleDao.updateDates(crawle.getId(), updated, durationStamp);
    }

    public void addError(String subject, String message, Crawle crawle){
        versioneye.domain.Error error = new versioneye.domain.Error();
        error.setSubject(subject);
        error.setErrormessage(message);
        if (crawle != null){
            error.setCrawle_id(crawle.getId());
        }
        System.out.println("subject: " + subject + " :message: " + message);
        errorDao.create(error);
    }

    public void setErrorDao(IErrorDao errorDao) {
        this.errorDao = errorDao;
    }

    public void setCrawleDao(ICrawleDao crawleDao) {
        this.crawleDao = crawleDao;
    }

}
