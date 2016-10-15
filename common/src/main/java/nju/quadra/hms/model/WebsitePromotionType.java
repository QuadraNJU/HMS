package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum WebsitePromotionType {
    TIME_PROMOTION,
    AREA_PROMOTION,
    VIPLEVEL_PROMOTION;

    @Override
    public String toString() {
        switch (this) {
            case TIME_PROMOTION:
                return "特定期间住宿折扣";
            case AREA_PROMOTION:
                return "特定商圈专属折扣";
            case VIPLEVEL_PROMOTION:
                return "会员等级折扣";
            default:
                return null;
        }
    }
}
