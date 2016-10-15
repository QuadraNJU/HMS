package nju.quadra.hms.vo;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;

import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class UserVO {
    /**
     * 用户名
     */
    public String username;
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
    /**
     * 会员类型
     */
    public MemberType memberType;
    /**
     * 生日
     */
    public Date birthday;
    /**
     * 企业名称
     */
    public String companyName;

    public UserVO(String username, String password, String name, String contact, UserType type, MemberType memberType, Date birthday, String companyName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = type;
        this.memberType = memberType;
        this.birthday = birthday;
        this.companyName = companyName;
    }
}
