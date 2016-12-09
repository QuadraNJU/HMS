package nju.quadra.hms.controller;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
class WebsitePromotionController {
    private WebsitePromotionController websitePromotionController;

    private ArrayList<WebsitePromotionVO> get(int hotelId) {
        try {
            return websitePromotionController.get(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage add(WebsitePromotionVO vo) {
        try {
            return websitePromotionController.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage delete(int promotionId) {
        try {
            return websitePromotionController.delete(promotionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private ResultMessage modify(WebsitePromotionVO vo) {
        try {
            return websitePromotionController.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
