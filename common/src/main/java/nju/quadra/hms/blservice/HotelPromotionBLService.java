package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

public interface HotelPromotionBLService {
    ArrayList<HotelPromotionVO> get(int hotelId);

    ResultMessage add(HotelPromotionVO vo);

    ResultMessage delete(int promotionId);

    ResultMessage modify(HotelPromotionVO vo);
}
