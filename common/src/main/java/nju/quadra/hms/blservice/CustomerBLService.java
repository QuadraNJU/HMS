package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * 负责实现客户信息管理所需要的服务
 */
public interface CustomerBLService {
    /**
     * 客户信息登记
     *
     * @param vo 客户信息值对象
     * @return 结果消息
     */
    ResultMessage register(UserVO vo);

    /**
     * 获得客户会员信息
     *
     * @param username 客户名
     * @return 客户会员信息的值对象
     */
    MemberVO getMemberInfo(String username);

    /**
     * 修改客户基本信息
     *
     * @param vo 客户信息值对象
     * @return 结果消息
     */
    ResultMessage modifyMemberInfo(MemberVO vo);

    /**
     * 登记会员
     *
     * @param vo 客户信息值对象
     * @return 结果消息
     */
    ResultMessage enroll(MemberVO vo);

    /**
     * 获得所有合作企业信息
     *
     * @return 所有企业名称和集合
     */
    ArrayList<String> getAllCompany();
}
