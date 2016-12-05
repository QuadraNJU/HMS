package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.HotelPromotionVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelPromotionController {
    private HotelPromotionController hotelPromotionController;

    public ArrayList<HotelPromotionVO> get(int hotelId) {
        try {
            return hotelPromotionController.get(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(HotelPromotionVO vo) {
        try {
            return hotelPromotionController.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int promotionId) {
        try {
            return hotelPromotionController.delete(promotionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(HotelPromotionVO vo) {
        try {
            return hotelPromotionController.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
