package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class UserBLService_Driver {
    public void drive(UserBLService userBLService) {
        try {
            ArrayList<UserVO> list = userBLService.getAll();
            UserVO vo = userBLService.get(list.get(0).username);
            userBLService.delete(vo.username);
            userBLService.add(vo);
            userBLService.modify(vo.username, vo);
            userBLService.login(vo.username, vo.password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
