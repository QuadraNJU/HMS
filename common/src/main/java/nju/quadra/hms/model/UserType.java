package nju.quadra.hms.model;

public enum UserType {
    CUSTOMER("客户"),
    HOTEL_STAFF("酒店工作人员"),
    WEBSITE_MARKETER("网站营销人员"),
    WEBSITE_MASTER("网站管理人员");

    final String showname;

    UserType(String showname) {
        this.showname = showname;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static UserType getById(int id) {
        return UserType.values()[id];
    }
}
