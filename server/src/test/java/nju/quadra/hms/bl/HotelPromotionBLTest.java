package nju.quadra.hms.bl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import nju.quadra.hms.bl.mockObject.MockHotelBL;
import nju.quadra.hms.bl.mockObject.MockHotelPromotionBL;
import nju.quadra.hms.blservice.promotionBL.*;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelPromotionPO;
import nju.quadra.hms.vo.HotelPromotionVO;
import nju.quadra.hms.vo.HotelVO;;
/**
 * Created by Ray on 16/11/5.
 */
public class HotelPromotionBLTest {
	HotelPromotionBLService hpBL;
	@Before
    public void init() {
        this.hpBL = new MockHotelPromotionBL();
    }

    @Test
    public void testGet() {
        assertNotNull(hpBL.get(0));
    }

    @Test
    public void testAdd() {
        HotelPromotionVO vo = new HotelPromotionVO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, hpBL.add(vo).result);
    }

    @Test
    public void testDelete() {
    	 HotelPromotionVO vo = new HotelPromotionVO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                 new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, hpBL.delete(vo.id).result);
    }

    @Test
    public void testModify() {
    	HotelPromotionVO vo = new HotelPromotionVO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, hpBL.modify(vo.id, vo).result);
    }

}
