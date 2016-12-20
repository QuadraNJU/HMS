package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.*;
import nju.quadra.hms.data.mysql.OrderDataServiceImpl;
import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.model.*;
import nju.quadra.hms.util.PassHash;
import nju.quadra.hms.po.OrderPO;
import nju.quadra.hms.vo.*;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OrderBLTest {
    private final OrderBLService orderBL = new OrderBL();
    private final HotelPromotionBLService hotelPromotionBL = new HotelPromotionBL();
    private final WebsitePromotionBLService websitePromotionBL = new WebsitePromotionBL();
    private final UserBLService userBL = new UserBL();
    private final HotelBLService hotelBL = new HotelBL();
    private final HotelRoomBLService hotelRoomBL = new HotelRoomBL();

    private final OrderDataService orderData = new OrderDataServiceImpl();

    @Test
    public void test1_makeOrder() {
        clean();
        // add a user
        ResultMessage result = userBL.add(new UserVO("TEST_customer", PassHash.hash("test"), "测试客户", "test", UserType.CUSTOMER));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // add a hotel staff
        result = userBL.add(new UserVO("TEST_staff", PassHash.hash("test"), "测试酒店工作人员", "test", UserType.HOTEL_STAFF));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // add a hotel
        result = hotelBL.add(new HotelVO(0, "TEST_Hotel", 9999, "test", "test", "test", "一星级", "TEST_staff"));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        HotelVO hotelVO = hotelBL.getByStaff("TEST_staff");
        assertNotNull(hotelVO);
        // add a hotel room
        result = hotelRoomBL.add(new HotelRoomVO(0, hotelVO.id, "TEST_room", 5, 120.0));
        HotelRoomVO roomVO = hotelRoomBL.getAll(hotelVO.id).get(0);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        assertNotNull(roomVO);
        // add a hotel promotion
        result = hotelPromotionBL.add(new HotelPromotionVO(0, hotelVO.id, "TEST_promotion", HotelPromotionType.MULTI_PROMOTION, LocalDate.now(), LocalDate.now(), 0.78, null));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // add a website promotion
        result = websitePromotionBL.add(new WebsitePromotionVO(0, "TEST_promotion", WebsitePromotionType.LEVEL_PROMOTION, LocalDate.now(), LocalDate.now(), 0.77, 9999, new HashMap<>()));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // get price
        ArrayList<String> persons = new ArrayList<>();
        persons.add("TEST_person");
        OrderVO order1 = new OrderVO(0, "TEST_customer", hotelVO.id, LocalDate.now(), LocalDate.now().plusDays(2), roomVO.id, 2, 1, persons, false, 0, OrderState.BOOKED, 0, "");
        OrderVO order2 = new OrderVO(0, "TEST_customer", hotelVO.id, LocalDate.now(), LocalDate.now().plusDays(3), roomVO.id, 4, 1, persons, false, 0, OrderState.BOOKED, 0, "");
        assertEquals(roomVO.price * 2 * 2 * 0.77, orderBL.getPrice(order1).finalPrice, 0.01);
        assertEquals(roomVO.price * 4 * 3 * 0.78 * 0.77, orderBL.getPrice(order2).finalPrice, 0.01);
        // add order
        order2.price = roomVO.price * 4 * 3 * 0.78 * 0.77;
        result = orderBL.add(order2);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // add an abnormal order directly to db
        try {
            orderData.insert(new OrderPO(0, "TEST_customer", hotelVO.id, LocalDate.now().minusDays(1), LocalDate.now(), roomVO.id, 1, 1, "[]", false, 12.34, OrderState.BOOKED, 0, ""));
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
        // get orders (abnormal order should be auto updated)
        ArrayList<OrderDetailVO> orders = orderBL.getByCustomer("TEST_customer");
        assertEquals(2, orders.size());
        assertEquals(OrderState.DELAYED, orders.get(1).state);
        // checkin & checkout
        result = orderBL.checkin(orders.get(0).id);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        result = orderBL.checkout(orders.get(0).id);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // undo
        result = orderBL.undoDelayed(orders.get(1).id, false);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // rank
        result = orderBL.addRank(new OrderRankVO(orders.get(0).id, LocalDate.now(), 3, "TEST_comment"));
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        // finish
        clean();
    }

    private void clean() {
        // delete orders
        try {
            for (OrderPO po : orderData.getByCustomer("TEST_customer")) {
                orderData.delete(po);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (WebsitePromotionVO wp : websitePromotionBL.get()) {
            if (wp.name.equals("TEST_promotion")) {
                websitePromotionBL.delete(wp.id);
            }
        }
        // delete hotel
        HotelVO hotelVO = hotelBL.getByStaff("TEST_staff");
        while (hotelVO != null) {
            // delete hotel room
            for (HotelRoomVO room : hotelRoomBL.getAll(hotelVO.id)) {
                hotelRoomBL.delete(room.id);
            }
            // delete promotions
            ArrayList<HotelPromotionVO> hps = hotelPromotionBL.get(hotelVO.id);
            for (HotelPromotionVO hp : hps) {
                hotelPromotionBL.delete(hp.id);
            }
            hotelBL.delete(hotelVO.id);
            hotelVO = hotelBL.getByStaff("TEST_staff");
        }
        // delete hotel staff
        userBL.delete("TEST_staff");
        // delete customer
        userBL.delete("TEST_customer");
    }

}
