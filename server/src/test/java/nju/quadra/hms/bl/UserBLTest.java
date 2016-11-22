package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.UserVO;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by admin on 2016/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserBLTest {
    UserBLService userBL;

    @Before
    public void init() {
        this.userBL = new UserBL();
    }

    @Test
    public void test1_loginWithNoSuchUsername() {
        String username = "havenothisusername";
        String password = "havenothispassword";
        ResultMessage resultMessage = userBL.login(username, password);
        assertNotEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
    }

    @Test
    public void test2_add() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordEncrypted1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER, MemberType.COMPANY, new Date(1996 - 1900, 11 - 1, 21 + 1), "TEST|companyname1");
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordEncrypted2", "TEST|name2", "TEST|contact2", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1994 - 1900, 12 - 1, 07 + 1), "TEST|companyname2");
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo2).result);
    }

    @Test
    public void test3_getAll() {
        ArrayList<UserVO> voarr = userBL.getAll();
        boolean b1 = false, b2 = false;
        for (UserVO vo : voarr) {
            if (vo.username.equals("TEST|username1")) {
                b1 = true;
            } else if (vo.username.equals("TEST|username2")) {
                b2 = true;
            }
        }
        assertTrue(b1 && b2);
    }

    @Test
    public void test4_get() {
        UserVO vo = userBL.get("TEST|username2");
        assertEquals("TEST|passwordEncrypted2", vo.password);
    }

    @Test
    public void test5_modify() {
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordEncrypted2", "TEST|name2AfterModified", "TEST|contact2", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1994 - 1900, 12 - 1, 07 + 1), "TEST|companyname2");
        ResultMessage resultMessage = userBL.modify(vo2);
        UserVO vo2Modified = userBL.get("TEST|username2");
        assertEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
        assertEquals("TEST|name2AfterModified", vo2Modified.name);
    }

    @Test
    public void test6_addDuplicated() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordEncrypted1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER, MemberType.COMPANY, new Date(1996 - 1900, 11 - 1, 21 + 1), "TEST|companyname1");
        ResultMessage resultMessage = userBL.add(vo1);
        assertNotEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
    }

    @Test
    public void test7_delete() {
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete("TEST|username1").result);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete("TEST|username2").result);
    }
}
