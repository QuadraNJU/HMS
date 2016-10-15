package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum HotelPromotionType {
    BIRTHDAY_PROMOTION,
    MULTI_PROMOTION,
    COMPANY_PROMOTION,
    TIME_PROMOTION;

    @Override
    public String toString() {
        switch (this) {
            case BIRTHDAY_PROMOTION:
                return "生日特惠折扣";
            case MULTI_PROMOTION:
                return "多间预订折扣";
            case COMPANY_PROMOTION:
                return "合作企业客户折扣";
            case TIME_PROMOTION:
                return "特定期间住宿折扣";
            default:
                return null;
        }
    }
}
