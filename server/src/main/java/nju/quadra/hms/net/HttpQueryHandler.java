package nju.quadra.hms.net;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by adn55 on 2016/11/22.
 */
public class HttpQueryHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] paths = httpExchange.getRequestURI().getPath().split("/");
        String result;
        if (paths.length < 3) {
            result = "Invalid request";
        } else {
            try {
                // get class and method
                Class<?> bl = Class.forName("nju.quadra.hms.bl." + paths[1]);
                Method[] methods = bl.getMethods();
                Method method = null;
                for (Method m : methods) {
                    if (m.getName().equals(paths[2])) {
                        method = m;
                        break;
                    }
                }

                // get HTTP request payload
                InputStream is = httpExchange.getRequestBody();
                byte[] buf = new byte[is.available()];
                is.read(buf);
                String payload = new String(buf);

                // process parameters
                int paramCount = method.getParameterCount();
                Object[] params = new Object[paramCount];
                Parameter[] requiredParams = method.getParameters();
                JsonElement jsonParams = new JsonParser().parse(payload);
                if (jsonParams.isJsonArray()) {
                    JsonArray paramsArray = jsonParams.getAsJsonArray();
                    for (int i = 0; i < paramsArray.size(); i++) {
                        if (i >= paramCount) break;
                        params[i] = new Gson().fromJson(paramsArray.get(i), requiredParams[i].getType());
                    }
                }

                // invoke method
                Object returnValue = method.invoke(bl.newInstance(), params);
                result = new Gson().toJson(returnValue);
            } catch (Exception e) {
                e.printStackTrace();
                result = "Invalid request";
            }
        }

        byte[] response = result.getBytes("utf-8");
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }
}
