package nju.quadra.hms.net;

import com.google.gson.Gson;

import java.io.IOException;

/**
 * Created by adn55 on 2016/11/23.
 */
public class HttpRemote {

    private String className;

    public HttpRemote(String className) {
        this.className = className;
    }

    public String invoke(String methodName, Object... params) throws IOException, ClassNotFoundException {
        String payload = new Gson().toJson(params);
        String response = HttpClient.post("/" + className + "/" + methodName, payload);
        return response;
    }

}
