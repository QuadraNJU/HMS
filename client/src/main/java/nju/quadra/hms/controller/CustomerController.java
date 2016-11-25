package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/11/25.
 */
public class CustomerController implements CustomerBLService {

    private HttpRemote remote;

    public CustomerController() {
        this.remote = new HttpRemote("CustomerBL");
    }

    @Override
    public ResultMessage register(UserVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "register", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    @Override
    public MemberVO getMemberInfo(String username) {
        try {
            return remote.invoke(MemberVO.class, "getMemberInfo", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public ResultMessage enroll(MemberVO vo) {
        try {
            return remote.invoke(ResultMessage.class, "enroll", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
