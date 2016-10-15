package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/14.
 */
public enum MemberType {
    PERSONAL,
    COMPANY;

    @Override
    public String toString() {
        switch (this) {
            case PERSONAL:
                return "个人会员";
            case COMPANY:
                return "企业会员";
            default:
                return null;
        }
    }
}
