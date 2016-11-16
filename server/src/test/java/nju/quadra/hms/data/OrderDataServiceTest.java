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
        OrderPO po = new OrderPO(0, "TEST|username", 654321, new Date(1996-1900, 11-1, 21), new Date(1996-1900, 11-1, 25), 7890, 1, 2, "TEST|alpha&beta", false, 99.9, OrderState.RANKED, 5, "TEST|comment: blabla");
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
            for (OrderPO po : result)
                if (po.getUsername().equals("TEST|username"))
                    return;
        } catch (Exception e) {
            e.printStackTrace();
        }
        fail();
    }

    @Test
    public void test3_Insert() {
        OrderPO po = new OrderPO(0, "TEST|username", 654321, new Date(1995-1900, 11-1, 21), new Date(1995-1900, 11-1, 25), 7890, 1, 2, "TEST|alpha&beta", false, 99.9, OrderState.DELAYED, -1, "");
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
            assertEquals("1996-11-21", result.get(0).getStartDate().toString());
            assertEquals("1995-11-25", result.get(1).getEndDate().toString());
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
            ArrayList<OrderPO> result = orderDataService.getByHotel(654321);
            OrderPO po = result.get(0);
            po.setUsername("TEST|username1");
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
            assertEquals(654321, result.get(0).getHotelId());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test8_Delete() {
        try {
            ArrayList<OrderPO> result = orderDataService.getByHotel(654321);
            for (OrderPO po : result)
                orderDataService.delete(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
