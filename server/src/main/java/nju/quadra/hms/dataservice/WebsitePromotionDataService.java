package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface WebsitePromotionDataService {
    ArrayList<WebsitePromotionPO> getAll() throws Exception;

    void insert(WebsitePromotionPO po) throws Exception;

    void delete(WebsitePromotionPO po) throws Exception;

    void update(WebsitePromotionPO po) throws Exception;
}
