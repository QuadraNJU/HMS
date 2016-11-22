package nju.quadra.hms.blservice.promotionBL;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelPromotionBLService {
    ArrayList<HotelPromotionVO> get(int hotelId);


    ResultMessage add(HotelPromotionVO vo);

    ResultMessage delete(int promotionId);

    ResultMessage modify(HotelPromotionVO vo);
}
