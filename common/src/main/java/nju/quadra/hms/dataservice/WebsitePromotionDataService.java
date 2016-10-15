package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface WebsitePromotionDataService {
    ArrayList<WebsitePromotionPO> getAll();

    void insert(WebsitePromotionPO po);

    void delete(WebsitePromotionPO po);

    void update(WebsitePromotionPO po);
}
