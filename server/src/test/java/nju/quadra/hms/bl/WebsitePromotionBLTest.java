package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockWebsitePromotionBL;
import nju.quadra.hms.blservice.promotionBL.WebsitePromotionBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.vo.WebsitePromotionVO;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class WebsitePromotionBLTest {
    WebsitePromotionBLService websitePromotionBL;
    @Before
    public void init() {
        websitePromotionBL = new MockWebsitePromotionBL();
    }

    @Test
    public void testGet() {
        assertEquals(true, websitePromotionBL.get().size() >= 0);
    }

    @Test
    public void testAdd() {
        WebsitePromotionVO vo = new WebsitePromotionVO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.add(vo).result);
    }

    @Test
    public void testDelete() {
        int promotionId = 1;
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.delete(promotionId).result);
    }

    @Test
    public void testModify() {
        int promotionId = 1;
        WebsitePromotionVO vo = new WebsitePromotionVO(1, "其实是12.12优惠啦", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 12, 10), new Date(2016, 12, 12), -11.11, -1, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, websitePromotionBL.modify(promotionId, vo).result);
    }
}
