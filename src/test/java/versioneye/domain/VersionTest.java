package versioneye.domain;

import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 5/29/13
 * Time: 1:25 PM
 */
public class VersionTest {

    @Test
    public void doTestCreateDateParser() throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = df.parse("2013-04-29 00:00:00");
        System.out.println("date: " + date);
    }

}
