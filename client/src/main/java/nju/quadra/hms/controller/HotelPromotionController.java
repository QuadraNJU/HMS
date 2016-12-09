package nju.quadra.hms.controller;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
class HotelPromotionController {
    private final HotelPromotionController hotelPromotionController;

    private ArrayList<HotelPromotionVO> get(int hotelId) {
        try {
            return hotelPromotionController.get(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage add(HotelPromotionVO vo) {
        try {
            return hotelPromotionController.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage delete(int promotionId) {
        try {
            return hotelPromotionController.delete(promotionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage modify(HotelPromotionVO vo) {
        try {
            return hotelPromotionController.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
