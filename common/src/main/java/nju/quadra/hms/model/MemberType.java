package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/14.
 */
public enum MemberType {
    NONE("非会员"),
    PERSONAL("个人会员"),
    COMPANY("企业会员");

    private String showname;

    MemberType(String showname) {
        this.showname = showname;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static MemberType getById(int id) {
        return MemberType.values()[id];
    }
}
