package nju.quadra.hms.util;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * Created by admin on 2016/11/14.
 */
public interface UserDAO {
    ArrayList<UserPO> getAll();

    ArrayList<UserPO> get(String username);

    ResultMessage insert(UserPO po);

    ResultMessage delete(UserPO po);

    ResultMessage update(UserPO po);

}
