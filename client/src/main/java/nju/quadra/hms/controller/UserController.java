package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.net.PassHash;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/23.
 */
public class UserController implements UserBLService {

    private HttpRemote remote;

    public UserController() {
        remote = new HttpRemote("UserBL");
    }

    @Override
    public ResultMessage login(String username, String password) {
        String encryptedPassword = PassHash.hash(password);
        try {
            return remote.invoke(ResultMessage.class, "login", username, encryptedPassword);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    @Override
    public ArrayList<UserVO> getAll() {
        try {
            return remote.invoke(new TypeToken<ArrayList<UserVO>>(){}.getType(), "getAll");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserVO get(String username) {
        try {
            return remote.invoke(UserVO.class, "get", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage add(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    @Override
    public ResultMessage delete(String username) {
        try {
            return remote.invoke(ResultMessage.class, "delete", username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    @Override
    public ResultMessage modify(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "modify", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
