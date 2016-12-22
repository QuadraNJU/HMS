package nju.quadra.hms.net;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import nju.quadra.hms.bl.CustomerBL;
import nju.quadra.hms.blservice.CustomerBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.UserVO;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class UserRegisterHandler implements HttpHandler {

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
                CustomerBLService customerBL = new CustomerBL();
                ResultMessage registerResult = customerBL.register(vo);
                result = new Gson().toJson(registerResult);
            } catch (Exception e) {
                result = "Invalid request";
            }
        } else {
            result = "Invalid request";
        }

        byte[] response = result.getBytes("UTF-8");
        httpExchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        httpExchange.sendResponseHeaders(200, response.length);
        OutputStream os = httpExchange.getResponseBody();
        os.write(response);
        os.close();
    }

}
