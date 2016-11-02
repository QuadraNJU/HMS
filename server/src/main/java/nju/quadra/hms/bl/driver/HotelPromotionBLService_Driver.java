package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.promotionBL.HotelPromotionBLService;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelPromotionBLService_Driver {
    public void drive(HotelPromotionBLService hotelPromotionBLService) {
        try {
            ArrayList<HotelPromotionVO> list = hotelPromotionBLService.get(1);
            hotelPromotionBLService.delete(list.get(0).id);
            hotelPromotionBLService.add(list.get(0));
            hotelPromotionBLService.modify(list.get(0).id, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
