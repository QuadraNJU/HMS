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
        vo.type = UserType.CUSTOMER;
        return new UserBL().add(vo);
    }

    @Override
    public MemberVO getMemberInfo(String username) {
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
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                vo.username = session.username;
            } else if (!session.userType.equals(UserType.WEBSITE_MASTER)) {
                return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
            }
        }

        try {
            UserPO user = userDataService.get(vo.username);
            user.setMemberType(vo.memberType);
            user.setBirthday(vo.birthday);
            user.setCompanyName(vo.companyName);
            userDataService.update(user);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage enroll(MemberVO vo) {
        return modifyMemberInfo(vo);
    }

    @Override
    public ArrayList<String> getAllCompany() {
        if (session != null) {
            if (!session.userType.equals(UserType.HOTEL_STAFF)) {
                return new ArrayList<>();
            }
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
