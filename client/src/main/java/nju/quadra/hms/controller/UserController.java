package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/23.
 */
public class UserController {

    private HttpRemote remote;

    public UserController() {
        remote = new HttpRemote("UserBL");
    }

    public ArrayList<UserVO> getAll() {
        try {
            return remote.invoke(new TypeToken<ArrayList<UserVO>>(){}.getType(), "getAll");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserVO get(String username) {
        try {
            return remote.invoke(UserVO.class, "get", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage delete(String username) {
        try {
            return remote.invoke(ResultMessage.class, "delete", username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modify(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "modify", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
