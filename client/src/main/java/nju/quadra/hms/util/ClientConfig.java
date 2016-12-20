package nju.quadra.hms.util;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ClientConfig {

    private String serverHost = "http://localhost:8081";
    private String username = "";
    private String password = "";

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
        saveToFile();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
        saveToFile();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        saveToFile();
    }

    private ClientConfig() {
    }

    private static ClientConfig config;
    private static final String configFile = "client_config.json";

    public static ClientConfig getConfig() {
        if (config == null) {
            loadFromFile();
        }
        return config;
    }

    /**
     * 从 JSON 文件中读取客户端配置
     */
    public static void loadFromFile() {
        try {
            FileInputStream is = new FileInputStream(configFile);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            is.close();
            config = new Gson().fromJson(new String(buffer), ClientConfig.class);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Read config file failed, using default config.");
            config = new ClientConfig();
            config.saveToFile();
        }
    }

    /**
     * 将客户端配置保存到 JSON 文件
     */
    public void saveToFile() {
        String json = new Gson().toJson(this);
        try {
            FileOutputStream os = new FileOutputStream(configFile);
            os.write(json.getBytes("UTF-8"));
            os.close();
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Save config file failed!");
        }
    }

}
