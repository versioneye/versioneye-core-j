package versioneye.persistence.mongodb;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: reiz
 * Date: 1/1/12
 * Time: 6:41 PM
 *
 */
public class MongoDB {

    private DB db;
    private String host;
    private Integer port;
    private String host2;
    private Integer port2;
    private String host3;
    private Integer port3;
    private String dbname;
    private String username;
    private String password;

    public MongoDB(){
        System.out.println("init MongoDB");
    }

    public DB getDb(){
        if (db == null || host == null || host.isEmpty() || host.trim().equals("localhost")){
            initDB();
        }
        return db;
    }

    public synchronized void initDB(){
        try{
            for (Map.Entry<String,String> k : System.getenv().entrySet()){
                System.out.println(k.getKey() + ": " + k.getValue());
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }


        System.out.println("initDB - MongoDB " + host + ":" + port);
        try {
            Mongo mongo = null;

            String db_host = System.getenv("DB_PORT_27017_TCP_ADDR");
            String db_port = System.getenv("DB_PORT_27017_TCP_PORT");
            System.out.println("DB_PORT_27017_TCP_ADDR: " + db_host + " DB_PORT_27017_TCP_PORT: " + db_port);
            if (db_host != null && !db_host.isEmpty() && db_port != null && !db_port.isEmpty()){
                host = db_host;
                port = new Integer(db_port);
            }

            String env = System.getenv("RAILS_ENV");
            if (env != null && !env.isEmpty()){
                if (env.toLowerCase().trim().equals("development")){
                    dbname = "veye_dev";
                } else if (env.toLowerCase().trim().equals("test")){
                    dbname = "veye_test";
                } else if (env.toLowerCase().trim().equals("production")) {
                    dbname = "veye_prod";
                } else if (env.toLowerCase().trim().equals("enterprise")) {
                    dbname = "veye_enterprise";
                } else {
                    dbname = "veye_enterprise";
                }
            }
            System.out.println("dbname: " + dbname);

            if (!dbname.equals("veye_enterprise")){
                String db_host_2 = System.getenv("MONGO_RS_2_ADDR");
                String db_port_2 = System.getenv("MONGO_RS_2_PORT");
                if (db_host_2 != null && !db_host_2.isEmpty() && db_port_2 != null && !db_port_2.isEmpty()){
                    host2 = db_host_2;
                    port2 = new Integer(db_port_2);
                }

                String db_host_3 = System.getenv("MONGO_RS_3_ADDR");
                String db_port_3 = System.getenv("MONGO_RS_3_PORT");
                if (db_host_3 != null && !db_host_3.isEmpty() && db_port_3 != null && !db_port_3.isEmpty()){
                    host3 = db_host_3;
                    port3 = new Integer(db_port_3);
                }
            } else {
                host2 = null;
                host3 = null;
            }

            MongoCredential mc = null;
            if (username != null && password != null && !username.trim().equals("") && !password.trim().equals("")){
                mc = MongoCredential.createCredential(username, dbname, password.toCharArray());
            }

            if (host2 != null && !host2.isEmpty() && host3 != null && !host3.isEmpty()){
                List replicaset = new ArrayList();
                replicaset.add(new ServerAddress(host, port));
                replicaset.add(new ServerAddress(host2, port2));
                replicaset.add(new ServerAddress(host3, port3));
                if (mc != null){
                    mongo = new MongoClient(replicaset, Arrays.asList(mc));
                } else {
                    mongo = new MongoClient(replicaset);
                }
                db = mongo.getDB(dbname);
                db.setReadPreference(ReadPreference.secondary());
                System.out.println("Connected to ReplicaSet ");
            } else {
                ServerAddress sa = new ServerAddress(host, port);
                if (mc != null){
                    mongo = new MongoClient(sa, Arrays.asList(mc));
                } else {
                    mongo = new MongoClient(sa);
                }
                db = mongo.getDB(dbname);
                db.setReadPreference(ReadPreference.primary());
                System.out.println("Connected to Single Node " + host + ":" + port);
            }

            System.out.println("getDB .. db is null .. create new db connection. MongoDB: " + this.toString() + " db: " + db.toString() );
        } catch (Exception ex){
            ex.printStackTrace();
        }
    }

    public String toString(){
        return "host: "+host+" host2: "+host2+" host3: " + host3;
    }

    public String getHost(){
        return this.host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getHost2() {
        return host2;
    }

    public void setHost2(String host2) {
        this.host2 = host2;
    }

    public int getPort2() {
        return port2;
    }

    public void setPort2(int port2) {
        this.port2 = port2;
    }

    public String getHost3() {
        return host3;
    }

    public void setHost3(String host3) {
        this.host3 = host3;
    }

    public int getPort3() {
        return port3;
    }

    public void setPort3(int port3) {
        this.port3 = port3;
    }

    public void setDbname(String dbname) {
        this.dbname = dbname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}