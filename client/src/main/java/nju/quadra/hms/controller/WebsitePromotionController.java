package nju.quadra.hms.controller;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class WebsitePromotionController {
    private WebsitePromotionController websitePromotionController;

    public ArrayList<WebsitePromotionVO> get(int hotelId) {
        try {
            return websitePromotionController.get(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(WebsitePromotionVO vo) {
        try {
            return websitePromotionController.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int promotionId) {
        try {
            return websitePromotionController.delete(promotionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(WebsitePromotionVO vo) {
        try {
            return websitePromotionController.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
