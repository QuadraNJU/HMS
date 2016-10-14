package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class WebsitePromotionDataService_Driver {
    public void drive(WebsitePromotionDataService websitePromotionDataService) {
        try {
            ArrayList<WebsitePromotionPO> list = websitePromotionDataService.getAll();
            websitePromotionDataService.delete(list.get(0));
            websitePromotionDataService.insert(list.get(0));
            websitePromotionDataService.update(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
