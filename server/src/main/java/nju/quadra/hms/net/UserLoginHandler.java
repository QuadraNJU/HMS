package nju.quadra.hms.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nju.quadra.hms.bl.UserBL;
import nju.quadra.hms.blservice.UserBLService;
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
public class UserLoginHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        // get HTTP request payload
        InputStream is = httpExchange.getRequestBody();
        byte[] buf = new byte[is.available()];
        is.read(buf);
        String payload = new String(buf);

        // process parameters
        JsonElement jsonParams = new JsonParser().parse(payload);
        LoginResult loginResult = null;
        if (jsonParams.isJsonArray()) {
            String username = jsonParams.getAsJsonArray().get(0).getAsString();
            String password = jsonParams.getAsJsonArray().get(1).getAsString();
            UserBLService userBL = new UserBL();
            ResultMessage blResult = userBL.login(username, password);
            if (blResult.result == ResultMessage.RESULT_SUCCESS) {
                // generate a new session
                String sessid = PassHash.hash(System.currentTimeMillis() + "");
                UserVO user = userBL.get(username);
                LoginSession session = new LoginSession(sessid, user.username, user.type);
                SessionManager.add(session);
                loginResult = new LoginResult(session);
            } else {
                loginResult = new LoginResult(blResult.result, blResult.message);
            }
        }

        String result = new Gson().toJson(loginResult);
        byte[] response = result.getBytes("UTF-8");
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }

}
