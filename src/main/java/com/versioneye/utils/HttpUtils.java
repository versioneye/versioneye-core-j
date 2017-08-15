package com.versioneye.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;


import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: robertreiz
 * Date: 6/14/13
 * Time: 3:34 PM
 */
public class HttpUtils {

    private final HtmlCleaner cleaner = new HtmlCleaner();
    private static String userAgent = "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2";


    public TagNode getSingleNode(Object[] objects){
        if (objects == null || objects.length == 0)
            return null;
        return (TagNode) objects[0];
    }


    public String getHttpResp(String address) throws Exception {
        System.setProperty("http.agent", userAgent);

        HttpGet httpget = new HttpGet( address );
        CloseableHttpClient httpclient = HttpClients.createDefault();
        httpget.setHeader("http.agent", userAgent);

        CloseableHttpResponse response = httpclient.execute(httpget);
        HttpEntity entity = response.getEntity();

        StringBuffer sb = new StringBuffer();
        if (entity == null) {
            return sb.toString();
        }

        String line;
        InputStream instream = entity.getContent();
        sb = new StringBuffer();
        BufferedReader input =  new BufferedReader(new InputStreamReader( instream ) );
        while((line = input.readLine()) != null)
            sb.append(line);
        input.close();
        instream.close();

        return sb.toString();
    }


    public String getHttpResponse(String address) throws Exception{
        return getHttpResp(address);
    }


    public String getHttpResponse(String address, String username, String password) throws Exception {
        System.setProperty("http.agent", userAgent);
        URL url = new URL(address);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setConnectTimeout(10000); // 10 seconds time out
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", userAgent);
        if (username != null && password != null){
            String user_pass = username + ":" + password;
            String encoded = Base64.encodeBase64String( user_pass.getBytes() );
            conn.setRequestProperty("Authorization", "Basic " + encoded);
        }
        if (conn.getResponseCode() != 200){
            System.out.println("ERROR: response code " + conn.getResponseCode() + " for " + address);
            return null;
        }
        String line = "";
        StringBuilder sb = new StringBuilder();
        BufferedReader input =  new BufferedReader(new InputStreamReader(conn.getInputStream()) );
        while((line = input.readLine())!=null)
            sb.append(line);
        input.close();
        return sb.toString();
    }


    public static int getResponseCode(String urlString) throws MalformedURLException, IOException {
        System.setProperty("http.agent", userAgent);
        URL url = new URL(urlString);
        HttpURLConnection huc =  (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.setConnectTimeout(5000); // 5 seconds
        huc.setRequestProperty("User-Agent", userAgent);
        huc.connect();
        return huc.getResponseCode();
    }


    public TagNode getPageForResource(String resource) throws Exception {
        return getPageForResource(resource, null, null);
    }


    public TagNode getPageForResource(String resource, String username, String password) throws Exception {
        String response = getHttpResponse(resource, username, password);
        try {
            return cleaner.clean(response);
        } catch (Exception ex) {
            System.out.println("ERROR: HTMLCleaner can not clean up " + resource);
            ex.printStackTrace();
        }
        return null;
    }


    public Object[] getObjectsFromPage(String resource, String xpath) throws Exception {
        System.setProperty("http.agent", userAgent);
        URL url = new URL(resource);
        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(10000); // 10 seconds time out
        conn.setRequestProperty("User-Agent", userAgent);
        TagNode page = cleaner.clean(conn.getInputStream());
        return page.evaluateXPath( xpath );
    }


    public Reader getResultReader(String resource) throws Exception {
        return getResultReader(resource, null, null);
    }


    public Reader getResultReader(String resource, String username, String password) throws Exception {
        System.setProperty("http.agent", userAgent);
        URL url = new URL(resource);
        HttpURLConnection connection =  (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(10000); // 10 seconds time out
        connection.setRequestProperty("User-Agent", userAgent);
        if (username != null && password != null){
            String user_pass = username + ":" + password;
            String encoded = Base64.encodeBase64String( user_pass.getBytes() );
            connection.setRequestProperty  ("Authorization", "Basic " + encoded);
        }
        return new InputStreamReader(connection.getInputStream());
    }


    public String getSingleValue(Object[] objects, HashMap<String, String> properties){
        if (objects == null || objects.length == 0)
            return null;
        TagNode node = (TagNode) objects[0];
        if (node.getText() == null)
            return null;
        String value = node.getText().toString().trim();
        if (properties != null && !properties.isEmpty())
            value = checkVariables(value, properties);
        return value;
    }


    public String checkVariables(String val, HashMap<String, String> properties){
        if (val == null || !val.startsWith("${"))
            return val;
        String key = val.replaceFirst(Pattern.quote("$"), "");
        key = key.replaceFirst(Pattern.quote("{"), "");
        key = key.replaceAll(Pattern.quote("}"), "");
        String propertyValue = properties.get(key.toLowerCase());
        if (propertyValue != null && !propertyValue.trim().equals(""))
            return propertyValue;
        else
            return val;
    }

}
