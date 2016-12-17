package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.CustomerBLService;
import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

public class CustomerBL implements CustomerBLService {

    private final LoginSession session;
    private final UserDataService userDataService = new UserDataServiceImpl();

    public CustomerBL() {
        session = null;
    }

    public CustomerBL(LoginSession session) {
        this.session = session;
    }

    @Override
    public ResultMessage register(UserVO vo) {
        // 注册用户只能为"会员"类型
        vo.type = UserType.CUSTOMER;
        return new UserBL().add(vo);
    }

    @Override
    public MemberVO getMemberInfo(String username) {
        // 安全性: 客户只能获取自己的会员信息，网站管理人员能获得所有会员信息
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                username = session.username;
            } else if (!session.userType.equals(UserType.WEBSITE_MASTER)) {
                return null;
            }
        }

        try {
            UserPO po = userDataService.get(username);
            if (po != null) {
                return new MemberVO(po.getUsername(), po.getMemberType(), po.getBirthday(), po.getCompanyName());
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage modifyMemberInfo(MemberVO vo) {
        // 安全性: 仅限网站管理人员调用或系统内部调用
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        switch (vo.memberType) {
            case PERSONAL:
                if (vo.birthday == null) {
                    return new ResultMessage("请填写生日信息");
                }
                break;
            case COMPANY:
                if (vo.companyName == null || vo.companyName.trim().isEmpty()) {
                    return new ResultMessage("请填写企业名称");
                }
        }

        try {
            UserPO user = userDataService.get(vo.username);
            user.setMemberType(vo.memberType);
            user.setBirthday(null);
            user.setCompanyName(null);
            if (vo.memberType.equals(MemberType.PERSONAL)) {
                user.setBirthday(vo.birthday);
            } else if (vo.memberType.equals(MemberType.COMPANY)) {
                user.setCompanyName(vo.companyName.trim());
            }
            userDataService.update(user);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage enroll(MemberVO vo) {
        // 安全性: 仅限客户调用
        if (session != null && !session.userType.equals(UserType.CUSTOMER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }
        // 防止重复登记
        MemberVO member = getMemberInfo(vo.username);
        if (!member.memberType.equals(MemberType.NONE)) {
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "您已经是会员，无需重复登记");
        }
        return new CustomerBL().modifyMemberInfo(vo);
    }

    @Override
    public ArrayList<String> getAllCompany() {
        // 安全性: 仅限酒店工作人员调用
        if (session != null && !session.userType.equals(UserType.HOTEL_STAFF)) {
            return new ArrayList<>();
        }

        try {
            ArrayList<UserPO> users = userDataService.getAll();
            ArrayList<String> companyList = new ArrayList<>();
            for (UserPO po : users) {
                if (po.getMemberType().equals(MemberType.COMPANY) && po.getCompanyName() != null
                        && !po.getCompanyName().isEmpty() && !companyList.contains(po.getCompanyName())) {
                    companyList.add(po.getCompanyName());
                }
            }
            return companyList;
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

}
