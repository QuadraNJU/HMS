package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class WebsitePromotionDataService_Stub implements WebsitePromotionDataService {
    @Override
    public ArrayList<WebsitePromotionPO> getAll() {
        ArrayList<WebsitePromotionPO> list = new ArrayList<>();
        WebsitePromotionPO po = new WebsitePromotionPO(1, "11.11优惠", WebsitePromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, -1, null);
        list.add(po);
        return list;
    }

    @Override
    public void insert(WebsitePromotionPO po) {
        System.out.println("Insert WebsitePromotionPO success");
    }

    @Override
    public void delete(WebsitePromotionPO po) {
        System.out.println("Delete WebsitePromotionPO success");
    }

    @Override
    public void update(WebsitePromotionPO po) {
        System.out.println("Update WebsitePromotionPO success");
    }
}
