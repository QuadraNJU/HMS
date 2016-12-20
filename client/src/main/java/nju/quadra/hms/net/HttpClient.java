package nju.quadra.hms.net;

import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.util.ClientConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    public static LoginSession session;

    public static String get(String path) throws IOException {
        String url = ClientConfig.getConfig().getServerHost() + path;
        if (session != null) {
            url += "/" + session.id;
        }
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);

        String resp = getStringFromInputStream(conn.getInputStream());
        conn.disconnect();
        return resp;
    }

    public static String post(String path, String payload) throws IOException {
        String url = ClientConfig.getConfig().getServerHost() + path;
        if (session != null) {
            url += "/" + session.id;
        }
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
        conn.setDoOutput(true);
        OutputStream os = conn.getOutputStream();
        os.write(payload.getBytes("UTF-8"));
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
        String result = os.toString("UTF-8");
        os.close();
        return result;
    }

}
