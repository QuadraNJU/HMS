package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/14.
 */
public enum UserType {
    CUSTOMER,
    HOTEL_STAFF,
    WEBSITE_MARKETER,
    WEBSITE_MANAGER;

    @Override
    public String toString() {
        switch (this) {
            case CUSTOMER:
                return "客户";
            case HOTEL_STAFF:
                return "酒店工作人员";
            case WEBSITE_MARKETER:
                return "网站营销人员";
            case WEBSITE_MANAGER:
                return "网站管理人员";
            default:
                return null;
        }
    }
}
