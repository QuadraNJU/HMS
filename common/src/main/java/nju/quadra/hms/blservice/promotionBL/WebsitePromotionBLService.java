package nju.quadra.hms.blservice.promotionBL;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface WebsitePromotionBLService {
    ArrayList<WebsitePromotionVO> get();

    ResultMessage add(WebsitePromotionVO vo);

    ResultMessage delete(int promotionId);

    ResultMessage modify(WebsitePromotionVO vo);
}
