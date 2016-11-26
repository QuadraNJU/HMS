package nju.quadra.hms.util;

import com.google.gson.Gson;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by adn55 on 2016/11/25.
 */
public class ClientConfigUtil {

    private static ClientConfig config = new ClientConfig();
    private static final String configFile = "client_config.json";

    public static ClientConfig getConfig() {
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
            e.printStackTrace();
            System.out.println("Read config file failed, using default config.");
            saveToFile();
        }
    }

    /**
     * 将客户端配置保存到 JSON 文件
     */
    public static void saveToFile() {
        String json = new Gson().toJson(config);
        try {
            FileOutputStream os = new FileOutputStream(configFile);
            os.write(json.getBytes("UTF-8"));
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Save config file failed!");
        }
    }

}
