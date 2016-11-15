package nju.quadra.hms.data;

import nju.quadra.hms.data.mysql.OrderDataServiceImpl;
import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by admin on 2016/11/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderDataServiceTest {
    private OrderDataService orderDataService;

    @Before
    public void init() {
        orderDataService = new OrderDataServiceImpl();
    }

    @Test
    public void test1_Insert() {
        OrderPO po = new OrderPO(123456, "TEST|username", 654321, new Date(1996-1900, 11-1, 21), new Date(1996-1900, 11-1, 25), 7890, 1, 2, "TEST|alpha&beta", false, 99.9, OrderState.RANKED, 5, "TEST|comment: blabla");
        try {
            orderDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test2_GetByState() {
        try {
            ArrayList<OrderPO> result = orderDataService.getByState(OrderState.RANKED);
            assertEquals("TEST|username", result.get(0).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test3_Insert() {
        OrderPO po = new OrderPO(123457, "TEST|username", 654321, new Date(1995-1900, 11-1, 21), new Date(1995-1900, 11-1, 25), 7890, 1, 2, "TEST|alpha&beta", false, 99.9, OrderState.DELAYED, -1, "");
        try {
            orderDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test4_GetByCustomer() {
        try {
            ArrayList<OrderPO> result = orderDataService.getByCustomer("TEST|username");
            assertEquals(2, result.size());
            assertEquals("TEST|username", result.get(0).getUsername());
            assertEquals("TEST|username", result.get(1).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test5_GetByHotel() {
        try {
            ArrayList<OrderPO> result = orderDataService.getByHotel(654321);
            assertEquals(2, result.size());
            assertEquals("TEST|username", result.get(0).getUsername());
            assertEquals("TEST|username", result.get(1).getUsername());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test6_Update() {
        try {
            OrderPO po = new OrderPO(123457, "TEST|username1", 654321, new Date(1995-1900, 11-1, 21), new Date(1995-1900, 11-1, 25), 7890, 1, 2, "TEST|gamma&delta", false, 99.9, OrderState.DELAYED, -1, "");
            orderDataService.update(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test7_GetByCustomer() {
        try {
            ArrayList<OrderPO> result = orderDataService.getByCustomer("TEST|username1");
            assertEquals("TEST|gamma&delta", result.get(0).getPersons());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }


}
