package nju.quadra.hms.bl.stub;

import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class UserBLService_Stub implements UserBLService {
    private ArrayList<UserVO> list;
    private UserVO vo;

    public UserBLService_Stub() {
        list = new ArrayList<>();
        vo = new UserVO("quadra", "61d1e734315a0db52c3572d7ab8a8b68ac327bb42699ffd80b4fcec9870e77f3",
                "Quadra", "10086", UserType.WEBSITE_MANAGER, null, null, null);
        list.add(vo);
    }

    @Override
    public ResultMessage login(String username, String password) {
        return new ResultMessage(ResultMessage.RESULT_ERROR, "Password error");
    }

    @Override
    public ArrayList<UserVO> getAll() {
        return list;
    }

    @Override
    public UserVO get(String username) {
        return vo;
    }

    @Override
    public ResultMessage add(UserVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(String username) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage modify(String username, UserVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
