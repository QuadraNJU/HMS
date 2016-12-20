package nju.quadra.hms.util;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by adn55 on 2016/12/20.
 */
public class ServerConfig {

    private int port = 8081;
    private String dbHost = "localhost";
    private String dbUser = "root";
    private String dbPass = "";
    private String dbName = "hms";

    private ServerConfig() {
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
        saveToFile();
    }

    public String getDbHost() {
        return dbHost;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
        saveToFile();
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
        saveToFile();
    }

    public String getDbPass() {
        return dbPass;
    }

    public void setDbPass(String dbPass) {
        this.dbPass = dbPass;
        saveToFile();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
        saveToFile();
    }

    private static ServerConfig config;
    private static final String configFile = "server_config.json";

    public static ServerConfig getConfig() {
        if (config == null) {
            loadFromFile();
        }
        return config;
    }

    /**
     * 从 JSON 文件中读取服务器端配置
     */
    public static void loadFromFile() {
        try {
            FileInputStream is = new FileInputStream(configFile);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            config = new Gson().fromJson(new String(buffer), ServerConfig.class);
        } catch (IOException e) {
            Logger.log(e);
            Logger.log("W", "Read config file failed, using default config.");
            config = new ServerConfig();
            config.saveToFile();
        }
    }

    /**
     * 将服务器端配置保存到 JSON 文件
     */
    public void saveToFile() {
        String json = new Gson().toJson(this);
        try {
            FileOutputStream os = new FileOutputStream(configFile);
            os.write(json.getBytes("UTF-8"));
            os.close();
        } catch (IOException e) {
            Logger.log(e);
            Logger.log("E", "Save config file failed!");
        }
    }

}
