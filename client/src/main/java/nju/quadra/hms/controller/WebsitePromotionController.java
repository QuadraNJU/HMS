package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.HotelPromotionVO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class WebsitePromotionController {
    private HttpRemote websitePromotionRemote;

    public WebsitePromotionController() {
        this.websitePromotionRemote = new HttpRemote("WebsitePromotionBL");
    }

    public ArrayList<WebsitePromotionVO> get(int hotelId) {
        try {
            return websitePromotionRemote.invoke(new TypeToken<ArrayList<WebsitePromotionVO>>(){}.getType(), "get", hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(WebsitePromotionVO vo) {
        try {
            return websitePromotionRemote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int promotionId) {
        try {
            return websitePromotionRemote.invoke(ResultMessage.class, "delete", promotionId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(WebsitePromotionVO vo) {
        try {
            return websitePromotionRemote.invoke(ResultMessage.class, "modify", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
