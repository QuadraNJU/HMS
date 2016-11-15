package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.HotelPromotionDataService;
import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelPromotionDataService_Driver {
    public void drive(HotelPromotionDataService hotelPromotionDataService) {
        try {
            ArrayList<HotelPromotionPO> list = hotelPromotionDataService.get(1);
            hotelPromotionDataService.delete(list.get(0));
            hotelPromotionDataService.insert(list.get(0));
            hotelPromotionDataService.update(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
