package nju.quadra.hms.po;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.UserVO;

import java.util.Date;


/**
 * Created by adn55 on 16/10/14.
 */
public class UserPO {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 姓名
     */
    private String name;
    /**
     * 联系方式
     */
    private String contact;
    /**
     * 用户类型
     */
    private UserType type;
    /**
     * 会员类型
     */
    private MemberType memberType;
    /**
     * 生日
     */
    private Date birthday;
    /**
     * 企业名称
     */
    private String companyName;

    public UserPO(String username, String password, String name, String contact, UserType type, MemberType memberType, Date birthday, String companyName) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.contact = contact;
        this.type = type;
        this.memberType = memberType;
        this.birthday = birthday;
        this.companyName = companyName;
    }

    public UserPO(UserVO vo) {
        this(vo.username, vo.password, vo.name, vo.contact, vo.type, vo.memberType, vo.birthday, vo.companyName);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
