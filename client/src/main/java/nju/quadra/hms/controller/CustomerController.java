package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/11/25.
 */
public class CustomerController {

    private HttpRemote remote;

    public CustomerController() {
        this.remote = new HttpRemote("CustomerBL");
    }

    public MemberVO getMemberInfo(String username) {
        try {
            return remote.invoke(MemberVO.class, "getMemberInfo", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage enroll(MemberVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "enroll", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
