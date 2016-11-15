package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.util.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import nju.quadra.hms.util.UserDAO;

import static org.junit.Assert.*;


/**
 * Created by RaUkonn on 2016/11/10.
 */
public class UserDAOTest {
    UserDAO userDAO;
    @Before
    public void init() {
        userDAO = new UserDAO_Mysql();
    }
    @Test
    public void testInsert() {
        userDAO.cleanAll();
        UserPO po = new UserPO("username111", "password111", "name111", "contact111", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996-1900,11-1,21), null);
        userDAO.insert(po);
        ArrayList<UserPO> selectResult = userDAO.getAll();
        assertEquals("1996-11-21", selectResult.get(0).getBirthday().toString());
        assertEquals(1, selectResult.size());
    }

    @Test
    public void testUpdate() {
        userDAO.cleanAll();
        UserPO po = new UserPO("username111", "password111", "name111", "contact111", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996-1900,11-1,21), null);
        userDAO.insert(po);
        UserPO po1 = new UserPO("username111", "password222", "name222", "contact222", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1997-1900,12-1,21), "testCompany");
        userDAO.update(po1);
        ArrayList<UserPO> selectResult = userDAO.getAll();
        assertEquals("1997-12-21", selectResult.get(0).getBirthday().toString());
        assertEquals(1, selectResult.size());
    }

    @Test
    public void testDelete() {
        userDAO.cleanAll();
        UserPO po1 = new UserPO("user1101", "password222", "name222", "contact222", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1997-1900,12-1,21), "testCompany");
        userDAO.insert(po1);
        userDAO.delete(po1);
        ArrayList<UserPO> selectResult = userDAO.getAll();
        assertEquals(0, selectResult.size());
    }

    @Test
    public void testGet() {
        userDAO.cleanAll();
        UserPO po1 = new UserPO("user1121", "password222", "name222", "contact222", UserType.HOTEL_STAFF, MemberType.COMPANY, new Date(1997-1900,12-1,21), "testCompany");
        UserPO po = new UserPO("username111", "password111", "name111", "contact111", UserType.CUSTOMER, MemberType.PERSONAL, new Date(1996-1900,11-1,21), null);
        userDAO.insert(po1);
        userDAO.insert(po);
        ArrayList<UserPO> selectResult = userDAO.get("user1121");
        assertEquals("user1121", selectResult.get(0).getUsername());
        assertEquals("password222", selectResult.get(0).getPassword());
        assertEquals(1, selectResult.size());

    }
}