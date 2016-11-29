package nju.quadra.hms.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nju.quadra.hms.bl.CustomerBL;
import nju.quadra.hms.bl.UserBL;
import nju.quadra.hms.model.LoginResult;
import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by adn55 on 2016/11/29.
 */
public class UserRegisterHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // get HTTP request payload
        InputStream is = httpExchange.getRequestBody();
        byte[] buf = new byte[is.available()];
        is.read(buf);
        String payload = new String(buf);

        // process parameters
        String result;
        JsonElement jsonParams = new JsonParser().parse(payload);
        if (jsonParams.isJsonArray()) {
            try {
                UserVO vo = new Gson().fromJson(jsonParams.getAsJsonArray().get(0), UserVO.class);
                CustomerBL customerBL = new CustomerBL();
                ResultMessage registerResult = customerBL.register(vo);
                result = new Gson().toJson(registerResult);
            } catch (Exception e) {
                result = "Invalid request";
            }
        } else {
            result = "Invalid request";
        }

        byte[] response = result.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }

}
