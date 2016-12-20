package nju.quadra.hms.model;

public enum HotelPromotionType {
    BIRTHDAY_PROMOTION("生日特惠折扣"),
    MULTI_PROMOTION("三间及以上预订特惠"),
    COMPANY_PROMOTION("合作企业客户折扣"),
    TIME_PROMOTION("特定期间住宿折扣");

    final String showname;

    HotelPromotionType(String showName) {
        this.showname = showName;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static HotelPromotionType getById(int id) {
        return HotelPromotionType.values()[id];
    }
}
