package nju.quadra.hms.bl.mockObject;

import nju.quadra.hms.blservice.promotionBL.WebsitePromotionBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class MockWebsitePromotionBL implements WebsitePromotionBLService {
    @Override
    public ArrayList<WebsitePromotionVO> get() {
        ArrayList<WebsitePromotionVO> list = new ArrayList<>();
        WebsitePromotionVO vo = new WebsitePromotionVO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        list.add(vo);
        return list;
    }

    @Override
    public ResultMessage add(WebsitePromotionVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(int promotionId) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage modify(int promotionId, WebsitePromotionVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
