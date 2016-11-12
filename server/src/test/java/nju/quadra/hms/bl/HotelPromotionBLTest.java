package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockHotelPromotionBL;
import nju.quadra.hms.blservice.promotionBL.HotelPromotionBLService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class HotelPromotionBLTest {
    HotelPromotionBLService hotelPromotionBL;
    @Before
    public void init() {
        hotelPromotionBL = new MockHotelPromotionBL();
    }

    @Test
    public void testGet() {
        int hotelId = 1;
        assertEquals(true, hotelPromotionBL.get(hotelId).size() >= 0);
    }

    @Test
    public void testAdd() {
        HotelPromotionVO vo = new HotelPromotionVO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.add(vo).result);
    }

    @Test
    public void testDelete() {
        int promotionId = 1;
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.delete(promotionId).result);
    }

    @Test
    public void testModify() {
        int promotionId = 1;
        HotelPromotionVO vo = new HotelPromotionVO(1, 1, "其实是12.12优惠啦", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 12, 10), new Date(2016, 12, 12), -11.11, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.modify(promotionId, vo).result);
    }
}
