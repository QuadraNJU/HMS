package nju.quadra.hms.blservice.userBL;

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

    ResultMessage modify(String username, UserVO vo);
}
