package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

public interface WebsitePromotionBLService {
    ArrayList<WebsitePromotionVO> get();

    ResultMessage add(WebsitePromotionVO vo);

    ResultMessage delete(int promotionId);

    ResultMessage modify(WebsitePromotionVO vo);
}
