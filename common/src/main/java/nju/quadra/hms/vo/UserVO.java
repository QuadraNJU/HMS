package nju.quadra.hms.vo;

import nju.quadra.hms.model.UserType;


/**
 * Created by adn55 on 16/10/15.
 */
public class UserVO {
    /**
     * 用户名
     */
    public final String username;
    /**
     * 密码
     */
    public String password;
    /**
     * 姓名
     */
    public String name;
    /**
     * 联系方式
     */
    public String contact;
    /**
     * 用户类型
     */
    public UserType type;

    public UserVO(String username, String password, String name, String contact, UserType type) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = type;
    }

    public UserVO(String username, String password, String name, String contact) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = UserType.CUSTOMER;
    }
}
