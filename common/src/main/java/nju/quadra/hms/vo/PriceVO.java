package nju.quadra.hms.vo;

import nju.quadra.hms.model.ResultMessage;

/**
 * Created by adn55 on 16/10/15.
 */
public class PriceVO {
    /**
     * 能否预定
     */
    public final ResultMessage result;
    /**
     * 订单原价
     */
    public double originalPrice;
    /**
     * 订单总价
     */
    public double finalPrice;
    /**
     * 所使用折扣
     */
    public HotelPromotionVO hotelPromotion;
    public WebsitePromotionVO websitePromotion;

    public PriceVO(double originalPrice, double finalPrice, HotelPromotionVO hotelPromotion, WebsitePromotionVO websitePromotion) {
        this.result = new ResultMessage(ResultMessage.RESULT_SUCCESS);
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.hotelPromotion = hotelPromotion;
        this.websitePromotion = websitePromotion;
    }

    public PriceVO(String errorMessage) {
        this.result = new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, errorMessage);
    }

}
