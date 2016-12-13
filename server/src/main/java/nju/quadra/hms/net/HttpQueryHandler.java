package nju.quadra.hms.net;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nju.quadra.hms.model.LoginSession;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;

/**
 * HTTP 请求处理类，用于解析 HTTP 请求并调用对应的方法
 */
class HttpQueryHandler implements HttpHandler {

    /**
     * 处理 HTTP 请求的方法
     * @param httpExchange HTTP 上下文
     * @throws IOException 请求处理异常
     */
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String[] paths = httpExchange.getRequestURI().getPath().split("/");
        String result;
        if (paths.length < 4 || !SessionManager.has(paths[3])) {
            result = "Invalid request";
        } else {
            try {
                // get class and method
                Class<?> bl = Class.forName("nju.quadra.hms.bl." + paths[1]);
                Method method = Arrays.stream(bl.getMethods()).filter(m -> m.getName().toLowerCase().equals(paths[2].toLowerCase())).findFirst().get();

                // get HTTP request payload
                InputStream is = httpExchange.getRequestBody();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                byte[] buf = new byte[is.available()];
                is.read(buf);
                os.write(buf);
                String payload = os.toString("UTF-8");
                os.close();
                is.close();

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
                try {
                    result = new Gson().toJson(method.invoke(bl.getConstructor(LoginSession.class).newInstance(SessionManager.get(paths[3])), params));
                } catch (NoSuchMethodException e) {
                    result = new Gson().toJson(method.invoke(bl.newInstance(), params));
                }

                // Debug
                System.out.println("Query: " + httpExchange.getRequestURI());
                System.out.println("Payload:\n" + payload);
                System.out.println("Response:\n" + result);
            } catch (Throwable e) {
                // e.printStackTrace();
                result = "Server exception: " + e.getClass().getSimpleName();
            }
        }

        byte[] response = result.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }

}
