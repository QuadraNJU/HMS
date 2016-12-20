package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

public interface UserDataService {
    ArrayList<UserPO> getAll() throws Exception;

    UserPO get(String username) throws Exception;

    void insert(UserPO po) throws Exception;

    void delete(UserPO po) throws Exception;

    void update(UserPO po) throws Exception;
}
