package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelPromotionDataService {
    ArrayList<HotelPromotionPO> get(int hotelId) throws Exception;

    HotelPromotionPO getById(int promotionId) throws Exception;

    void insert(HotelPromotionPO po) throws Exception;

    void delete(HotelPromotionPO po) throws Exception;

    void update(HotelPromotionPO po) throws Exception;
}
