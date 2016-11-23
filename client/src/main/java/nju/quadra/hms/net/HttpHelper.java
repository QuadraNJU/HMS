package nju.quadra.hms.net;

import com.google.gson.Gson;
import nju.quadra.hms.model.ResultMessage;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by adn55 on 2016/11/23.
 */
public class HttpHelper {

    private static String server = "http://localhost:8081";

    private final Class type;

    public HttpHelper(Class type) {
        this.type = type;
    }

    public Object invoke(String className, String methodName, Object... params) throws IOException {
        String url = server + "/" + className + "/" + methodName;
        String payload = new Gson().toJson(params);
        System.out.println(url);
        System.out.println(payload);

        URLConnection _conn = new URL(url).openConnection();
        _conn.setConnectTimeout(5000);
        _conn.setReadTimeout(5000);
        _conn.setDoOutput(true);
        OutputStream os = _conn.getOutputStream();
        os.write(payload.getBytes("utf-8"));
        os.close();

        String resp;
        if (_conn instanceof HttpsURLConnection) {
            HttpsURLConnection conn = (HttpsURLConnection) _conn;
            resp = getStringFromInputStream(conn.getInputStream());
            conn.disconnect();
        } else {
            HttpURLConnection conn = (HttpURLConnection) _conn;
            resp = getStringFromInputStream(conn.getInputStream());
            conn.disconnect();
        }

        return new Gson().fromJson(resp, this.type);
    }

    private static String getStringFromInputStream(InputStream is) throws IOException {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();
        os.close();
        return state;
    }

    public static void main(String[] args) {
        try {
            ResultMessage result = (ResultMessage) new HttpHelper(ResultMessage.class).invoke("UserBL", "login", "test", "test");
            System.out.println(result.message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
