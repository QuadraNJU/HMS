package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.OrderPO;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.util.OrderDAO;
import nju.quadra.hms.util.OrderDAO_Mysql;
import nju.quadra.hms.util.UserDAO;
import nju.quadra.hms.util.UserDAO_Mysql;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;


/**
 * Created by RaUkonn on 2016/11/10.
 */
public class OrderDAOTest {
    OrderDAO orderDAO;
    @Before
    public void init() {
        orderDAO = new OrderDAO_Mysql();
    }
    @Test
    public void testInsert() {
        ArrayList<String> persons = new ArrayList<>();
        persons.add("小马哥");
        persons.add("saber和小姐姐们");
        orderDAO.insert(new OrderPO(1, "quadra2", 1, new Date(2016-1900, 11-1, 11), new Date(2016-1900, 11-1, 12), 1, 1, 2,
                persons, false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处"));

        ArrayList<OrderPO> arr = orderDAO.selectByCustomer("quadra2");
        assertEquals(arr.get(0).getPersons(), "小马哥&saber和小姐姐们");
    }

    @Test
    public void testUpdate() {
        ArrayList<String> persons = new ArrayList<>();
        persons.add("啊啊啊");
        persons.add("不不不");
        orderDAO.update(new OrderPO(1, "quadra2", 1, new Date(2016-1900, 11-1, 11), new Date(2016-1900, 11-1, 12), 1, 1, 2,
                persons, false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处"));

        ArrayList<OrderPO> arr = orderDAO.selectByCustomer("quadra2");
        assertEquals(arr.get(0).getPersons(), "啊啊啊&不不不");
    }

}