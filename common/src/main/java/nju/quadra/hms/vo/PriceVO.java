package nju.quadra.hms.vo;

/**
 * Created by adn55 on 16/10/15.
 */
public class PriceVO {
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
        this.originalPrice = originalPrice;
        this.finalPrice = finalPrice;
        this.hotelPromotion = hotelPromotion;
        this.websitePromotion = websitePromotion;
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof PriceVO) {
//            PriceVO pvo = (PriceVO)obj;
//            if(originalPrice == pvo.originalPrice && finalPrice == pvo.finalPrice
//                    && hotelPromotion.equals(pvo.hotelPromotion)
//                    && websitePromotion.equals(pvo.websitePromotion)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
