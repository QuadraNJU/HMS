package nju.quadra.hms.model;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adn55 on 16/10/14.
 */
public enum UserType {
    CUSTOMER("客户"),
    HOTEL_STAFF("酒店工作人员"),
    WEBSITE_MARKETER("网站营销人员"),
    WEBSITE_MASTER("网站管理人员");

    String showname;

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
    public static UserType getByShowname(String showname) {
        UserType[] userTypes = UserType.values();
        for(UserType ut: userTypes) {
            if(ut.showname.equals(showname))
                return ut;
        }
        return null;
    }
}
