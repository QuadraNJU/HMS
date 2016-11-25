package nju.quadra.hms.util;

import com.google.gson.Gson;

/**
 * Created by adn55 on 2016/11/25.
 */
public class ClientConfig {

    private String serverHost = "http://localhost:8081";

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

}
