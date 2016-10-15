package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class UserDataService_Driver {
    public void drive(UserDataService userDataService) {
        try {
            ArrayList<UserPO> list = userDataService.getAll();
            UserPO po = userDataService.select("quadra");
            userDataService.delete(list.get(0));
            userDataService.insert(list.get(0));
            userDataService.update(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
