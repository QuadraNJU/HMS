package nju.quadra.hms.model;

public enum WebsitePromotionType {
    TIME_PROMOTION("特定期间住宿折扣"),
    LEVEL_PROMOTION("会员等级折扣");

    final String showname;

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
