package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelPromotionDataService {
    ArrayList<HotelPromotionPO> get(int hotelId);

    void insert(HotelPromotionPO po);

    void delete(HotelPromotionPO po);

    void update(HotelPromotionPO po);
}
