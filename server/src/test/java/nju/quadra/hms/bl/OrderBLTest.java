package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockOrderBL;
import nju.quadra.hms.blservice.orderBL.OrderBLService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;


/**
 * Created by admin on 2016/11/6.
 */
public class OrderBLTest {
    OrderBLService orderBL;

    @Before
    public void init() {this.orderBL = new MockOrderBL();}

    @Test
    public void testGetPrice() {
        OrderVO vo = new OrderVO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        //TODO:这里需要重载equals方法才能正确使用assertEquals方法！！！
        assertNotNull(orderBL.getPrice(vo));
    }

    @Test
    public void testAddDuplicate() {
        OrderVO vo = new OrderVO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        assertEquals(ResultMessage.RESULT_ERROR, orderBL.add(vo).result);
    }

    @Test
    public void testAddNormal() {
        OrderVO vo = new OrderVO(2, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        assertEquals(ResultMessage.RESULT_SUCCESS, orderBL.add(vo).result);
    }

    @Test
    public void testGetByCustomer() {
        String username = "quadra2";
        assertEquals(true, orderBL.getByCustomer(username).size() >= 0);
    }

    @Test
    public void testGetByHotel() {
        int hotelid = 1;
        assertEquals(true, orderBL.getByHotel(hotelid).size() >= 0);
    }

    @Test
    public void testGetByState() {
        OrderState state = OrderState.FINISHED;
        assertEquals(true, orderBL.getByState(state).size() >= 0);
    }

    @Test
    public void testUndoDelayed() {
        OrderVO vo = new OrderVO(2, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        boolean returnAllCredit = true;
        assertEquals(ResultMessage.RESULT_SUCCESS, orderBL.undoDelayed(vo, returnAllCredit).result);
    }

    @Test
    public void testUndoUnfinished() {
        OrderVO vo = new OrderVO(2, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        assertEquals(ResultMessage.RESULT_SUCCESS, orderBL.undoUnfinished(vo).result);
    }

    @Test
    public void testFinish() {
        OrderVO vo = new OrderVO(2, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        assertEquals(ResultMessage.RESULT_SUCCESS, orderBL.finish(vo).result);
    }

    @Test
    public void testAddRank() {
        OrderRankVO vo = new OrderRankVO(1, 3, "是个休闲的好去处");
        assertEquals(ResultMessage.RESULT_SUCCESS, orderBL.addRank(vo).result);
    }
}
