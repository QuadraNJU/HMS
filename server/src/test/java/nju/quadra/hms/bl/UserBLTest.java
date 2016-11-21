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
    public void test1_LoginWtihNoSuchUsername() {
        String username = "havenothisusername";
        String password = "havenothispassword";
        ResultMessage resultMessage = userBL.login(username, password);
        assertEquals(ResultMessage.RESULT_ERROR, resultMessage.result);
        assertEquals("不存在该用户，请确认所输入的用户名是否正确", resultMessage.message);
    }

    @Test
    public void test2_add() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordCyphered1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER, MemberType.COMPANY, new Date(1996-1900, 11-1, 21+1), "TEST|companyname1");
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordCyphered2", "TEST|name2", "TEST|contact2", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1994-1900, 12-1, 07+1), "TEST|companyname2");
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo2).result);
    }

    @Test
    public void test3_getAll() {
        ArrayList<UserVO> voarr = userBL.getAll();
        assertEquals(2, voarr.size());
        assertEquals("TEST|username1", voarr.get(0).username);
    }

    @Test
    public void test4_get() {
        UserVO vo = userBL.get("TEST|username2");
        assertEquals("TEST|passwordCyphered2", vo.password);
    }

    @Test
    public void test5_modify() {
        UserVO vo2 = new UserVO("TEST|username2", "TEST|passwordCyphered2", "TEST|name2AfterModified", "TEST|contact2", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1994-1900, 12-1, 07+1), "TEST|companyname2");
        ResultMessage resultMessage = userBL.modify(vo2);
        UserVO vo2Modified = userBL.get("TEST|username2");
        assertEquals(ResultMessage.RESULT_SUCCESS, resultMessage.result);
        assertEquals("TEST|name2AfterModified", vo2Modified.name);
    }

    @Test
    public void test6_addDuplicated() {
        UserVO vo1 = new UserVO("TEST|username1", "TEST|passwordCyphered1", "TEST|name1", "TEST|contact1", UserType.CUSTOMER, MemberType.COMPANY, new Date(1996-1900, 11-1, 21+1), "TEST|companyname1");
        ResultMessage resultMessage = userBL.add(vo1);
        assertEquals(ResultMessage.RESULT_ERROR, resultMessage.result);
        assertEquals("用户名已存在，请重新输入", resultMessage.message);
    }

    @Test
    public void test7_delete() {
        ArrayList<UserVO> voarr = userBL.getAll();
        for(UserVO vo: voarr) {
            assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete(vo.username).result);
        }
    }
}
