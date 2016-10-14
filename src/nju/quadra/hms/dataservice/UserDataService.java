package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface UserDataService {
    ArrayList<UserPO> getAll();

    UserPO select(String username);

    void insert(UserPO po);

    void delete(UserPO po);

    void update(UserPO po);
}
