package nju.quadra.hms.controller;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/11/25.
 */
public class CustomerController {

    private HttpRemote userRemote, customerRemote;

    public CustomerController() {
        this.userRemote = new HttpRemote("UserBL");
        this.customerRemote = new HttpRemote("CustomerBL");
    }

    public UserVO getUserInfo(String username) {
        try {
            return userRemote.invoke(UserVO.class, "get", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public double getCurrentCredit(String username) {
        try {
            return customerRemote.invoke(double.class, "getCurrentCredit", username);
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    public ResultMessage modifyUserInfo(UserVO vo) {
        try {
            return userRemote.invoke(ResultMessage.class, "modifyBasicInfo", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public MemberVO getMemberInfo(String username) {
        try {
            return customerRemote.invoke(MemberVO.class, "getMemberInfo", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage enroll(MemberVO vo) {
        try {
            return customerRemote.invoke(ResultMessage.class, "enroll", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
