package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum WebsitePromotionType {
    TIME_PROMOTION("特定期间住宿折扣"),
    AREA_PROMOTION("特定商圈专属折扣"),
    VIPLEVEL_PROMOTION("会员等级折扣");

    String showname;

    WebsitePromotionType(String showname) {
        this.showname = showname;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static WebsitePromotionType getById(int id) {
        return WebsitePromotionType.values()[id];
    }
}
