package nju.quadra.hms.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by adn55 on 2016/11/23.
 */
public class HttpClient {

    private static String server = "http://localhost:8081";

    public static String getServer() {
        return server;
    }

    public static void setServer(String server) {
        HttpClient.server = server;
    }

    public static String get(String path) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(server + path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);

        String resp = getStringFromInputStream(conn.getInputStream());
        conn.disconnect();
        return resp;
    }

    public static String post(String path, String payload) throws IOException {
        HttpURLConnection conn = (HttpURLConnection) new URL(server + path).openConnection();
        conn.setConnectTimeout(5000);
        conn.setReadTimeout(5000);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes("utf-8"));
        os.close();

        String resp = getStringFromInputStream(conn.getInputStream());
        conn.disconnect();
        return resp;
    }

    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String result = os.toString();
        os.close();
        return result;
    }

}
