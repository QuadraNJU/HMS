package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.util.UserDataService;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by RaUkonn on 2016/11/10.
 */
public class UserDataServiceTest {
    @Test
    public void testInsert() {
        UserPO po = new UserPO("username111", "password111", "name111", "contact111", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996,11,21), null);
        UserDataService.insert(po);
        UserPO po1 = new UserPO("username222", "password222", "name222", "contact222", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996,12,21), null);
        UserDataService.insert(po1);
        ArrayList<UserPO> selectResult = UserDataService.selectAll();
        assertEquals("1996-11-21", selectResult.get(0).getBirthday().toString());
    }
}