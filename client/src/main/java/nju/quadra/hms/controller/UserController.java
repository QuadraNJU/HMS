package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/23.
 */
class UserController {

    private final UserBLService userBL;

    public UserController() {
        userBL = BLServiceFactory.getUserBLService();
    }

    public ArrayList<UserVO> getAll() {
        try {
            return userBL.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public UserVO get(String username) {
        try {
            return userBL.get(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(UserVO vo) {
        try {
            return userBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage delete(String username) {
        try {
            return userBL.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modify(UserVO vo) {
        try {
            return userBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
