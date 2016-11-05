package nju.quadra.hms.bl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import nju.quadra.hms.bl.mockObject.MockWebsitePromotionBL;
import nju.quadra.hms.blservice.promotionBL.WebsitePromotionBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.vo.WebsitePromotionVO;

/**
 * Created by Ray on 16/11/5.
 */
public class WebsitePromotionBLTest {
	WebsitePromotionBLService wpBL;
	@Before
    public void init() {
        this.wpBL = new MockWebsitePromotionBL();
    }

    @Test
    public void testGetAll() {
        assertNotNull(wpBL.get());
    }

    @Test
    public void testAdd() {
    	WebsitePromotionVO vo = new WebsitePromotionVO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, wpBL.add(vo).result);
    }

    @Test
    public void testDelete() {
    	WebsitePromotionVO vo = new WebsitePromotionVO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, wpBL.delete(vo.id).result);
    }

    @Test
    public void testModify() {
    	WebsitePromotionVO vo = new WebsitePromotionVO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, wpBL.modify(vo.id, vo).result);
    }

}
