package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;

public interface WebsitePromotionDataService {
    ArrayList<WebsitePromotionPO> getAll() throws Exception;

    WebsitePromotionPO getById(int id) throws Exception;

    void insert(WebsitePromotionPO po) throws Exception;

    void delete(WebsitePromotionPO po) throws Exception;

    void update(WebsitePromotionPO po) throws Exception;
}
