package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.UserVO;
import org.junit.*;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;

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
    public void test1_LoginWithNoSuchUsername() {
        String username = "havenothisusername";
        String password = "havenothispassword";
        ResultMessage resultMessage = userBL.login(username, password);
        assertNotEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
    }

    @Test
    public void test2_Add() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordEncrypted1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER);
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordEncrypted2", "TEST|name2", "TEST|contact2", UserType.HOTEL_STAFF);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo2).result);
    }

    @Test
    public void test3_GetAll() {
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
    public void test4_Get() {
        UserVO vo = userBL.get("TEST|username2");
        assertEquals("TEST|passwordEncrypted2", vo.password);
    }

    @Test
    public void test5_Modify() {
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordEncrypted2", "TEST|name2AfterModified", "TEST|contact2", UserType.HOTEL_STAFF);
        ResultMessage resultMessage = userBL.modify(vo2);
        UserVO vo2Modified = userBL.get("TEST|username2");
        assertEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
        assertEquals("TEST|name2AfterModified", vo2Modified.name);
    }

    @Test
    public void test6_AddDuplicated() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordEncrypted1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER);
        ResultMessage resultMessage = userBL.add(vo1);
        assertNotEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
    }

    @Test
    public void test7_Delete() {
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete("TEST|username1").result);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete("TEST|username2").result);
    }
}
