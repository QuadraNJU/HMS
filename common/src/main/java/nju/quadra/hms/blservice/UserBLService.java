package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface UserBLService {
    ResultMessage login(String username, String password);

    ArrayList<UserVO> getAll();

    UserVO get(String username);

    ResultMessage add(UserVO vo);

    ResultMessage delete(String username);

    ResultMessage modifyBasicInfo(UserVO vo);

    ResultMessage modify(UserVO vo);
}
