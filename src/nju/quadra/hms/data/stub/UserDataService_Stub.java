package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class UserDataService_Stub implements UserDataService {
    private ArrayList<UserPO> list;
    private UserPO po;

    public UserDataService_Stub() {
        list = new ArrayList<>();
        po = new UserPO("quadra", "61d1e734315a0db52c3572d7ab8a8b68ac327bb42699ffd80b4fcec9870e77f3",
                "Quadra", "10086", UserType.WEBSITE_MANAGER, null, null, null);
        list.add(po);
    }

    @Override
    public ArrayList<UserPO> getAll() {
        return list;
    }

    @Override
    public UserPO select(String username) {
        return po;
    }

    @Override
    public void insert(UserPO po) {
        System.out.println("Insert UserPO success");
    }

    @Override
    public void delete(UserPO po) {
        System.out.println("Delete UserPO success");
    }

    @Override
    public void update(UserPO po) {
        System.out.println("Update UserPO success");
    }
}
