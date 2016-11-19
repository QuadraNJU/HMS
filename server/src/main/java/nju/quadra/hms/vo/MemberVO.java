package nju.quadra.hms.vo;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.po.UserPO;

import java.util.Date;


/**
 * Created by adn55 on 16/10/15.
 */
public class MemberVO {
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

    public MemberVO(MemberType memberType, Date birthday, String companyName) {
        this.memberType = memberType;
        this.birthday = birthday;
        this.companyName = companyName;
    }

    public MemberVO(UserPO po) {
        this(po.getMemberType(), po.getBirthday(), po.getCompanyName());
    }
}
