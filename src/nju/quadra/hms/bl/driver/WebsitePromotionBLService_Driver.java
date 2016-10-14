package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.promotionBL.WebsitePromotionBLService;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class WebsitePromotionBLService_Driver {
    public void drive(WebsitePromotionBLService websitePromotionBLService) {
        try {
            ArrayList<WebsitePromotionVO> list = websitePromotionBLService.get();
            websitePromotionBLService.delete(list.get(0).id);
            websitePromotionBLService.add(list.get(0));
            websitePromotionBLService.modify(list.get(0).id, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
