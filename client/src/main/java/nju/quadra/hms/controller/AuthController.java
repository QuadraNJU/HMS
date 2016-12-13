package nju.quadra.hms.controller;

import nju.quadra.hms.model.LoginResult;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.util.PassHash;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 2016/11/29.
 */
public class AuthController {

    private final HttpRemote remote;

    public AuthController() {
        this.remote = new HttpRemote("AuthService");
    }

    public ResultMessage login(String username, String password) {
        String encryptedPassword = PassHash.hash(password);
        try {
            LoginResult loginResult = remote.invoke(LoginResult.class, "login", username, encryptedPassword);
            if (loginResult.result == ResultMessage.RESULT_SUCCESS) {
                // save the session for further usage
                HttpClient.session = loginResult.session;
            }
            return loginResult;
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage register(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "register", vo);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
