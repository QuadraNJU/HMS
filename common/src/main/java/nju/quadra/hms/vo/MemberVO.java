package nju.quadra.hms.vo;

import nju.quadra.hms.model.MemberType;

import java.time.LocalDate;


/**
 * Created by adn55 on 16/10/15.
 */
public class MemberVO {
	/**
     * 会员名
     */
    public String username;
    /**
     * 会员类型
     */
    public MemberType memberType;
    /**
     * 生日
     */
    public LocalDate birthday;
    /**
     * 企业名称
     */
    public String companyName;

    public MemberVO(String username, MemberType memberType, LocalDate birthday, String companyName) {
        this.username = username;
        this.memberType = memberType;
        this.birthday = birthday;
        this.companyName = companyName;
    }
}
