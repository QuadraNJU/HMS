package nju.quadra.hms.net;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;

public class HttpRemote {

    private final String className;

    public HttpRemote(String className) {
        this.className = className;
    }

    public <T> T invoke(Type returnType, String methodName, Object... params) throws IOException {
        String payload = new Gson().toJson(params);
        String response = HttpClient.post("/" + className + "/" + methodName, payload);
        return new Gson().fromJson(response, returnType);
    }

}
