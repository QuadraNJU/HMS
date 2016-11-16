package nju.quadra.hms.data;

import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by adn55 on 2016/11/15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserDataServiceTest {

    private UserDataService userDataService;

    @Before
    public void init() {
        userDataService = new UserDataServiceImpl();
    }

    @Test
    public void test1_Insert() {
        UserPO po = new UserPO("TEST|user", "TEST|pass", "JUnit", "123456", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996-1900,11-1,21+1), null);
        try {
            userDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test2_Get() {
        try {
            UserPO po = userDataService.get("TEST|user");
            assertEquals(po.getName(), "JUnit");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test3_Update() {
        UserPO po = new UserPO("TEST|user", "TEST|pass", "JUnitMod", "123456", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996-1900,11-1,21+1), null);
        try {
            userDataService.update(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test4_Get() {
        try {
            UserPO po = userDataService.get("TEST|user");
            assertEquals(po.getName(), "JUnitMod");
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test5_Delete() {
        try {
            UserPO po = userDataService.get("TEST|user");
            userDataService.delete(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test6_Get() {
        try {
            UserPO po = userDataService.get("TEST|user");
            assertNull(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
