package nju.quadra.hms.bl;

import nju.quadra.hms.util.Logger;
import nju.quadra.hms.blservice.UserBLService;
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

public class UserBL implements UserBLService {
    private final LoginSession session;
    private final UserDataService userDataService = new UserDataServiceImpl();

    public UserBL() {
        session = null;
    }

    public UserBL(LoginSession session) {
        this.session = session;
    }

    @Override
    public ResultMessage login(String username, String password) {
        try {
            UserPO po = userDataService.get(username);
            if (po != null && password.equals(po.getPassword())) {
                return new ResultMessage(ResultMessage.RESULT_SUCCESS);
            }
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
        return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "用户名或密码错误，请重新输入");
    }


    @Override
    public ArrayList<UserVO> getAll() {
        // 安全性: 仅限网站管理人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ArrayList<>();
        }

        ArrayList<UserVO> voarr = new ArrayList<>();
        try {
            ArrayList<UserPO> poarr = userDataService.getAll();
            for (UserPO po : poarr) {
                voarr.add(UserBL.toVO(po));
            }
        } catch (Exception e) {
            Logger.log(e);
        }
        return voarr;
    }

    @Override
    public UserVO get(String username) {
        // 安全性: 仅限客户访问自己的个人信息
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                username = session.username;
            } else {
                return null;
            }
        }

        try {
            UserPO po = userDataService.get(username);
            return UserBL.toVO(po);
        } catch (Exception e) {
            Logger.log(e);
        }
        return null;
    }

    @Override
    public ResultMessage add(UserVO vo) {
        // 安全性: 仅限网站管理人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        UserPO po = UserBL.toPO(vo);
        try {
            if (vo.username.isEmpty() || vo.password.isEmpty() || vo.contact.isEmpty() || vo.name.isEmpty()) {
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "用户信息不完整，请重新输入");
            }
            if (userDataService.get(vo.username) != null) {
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "用户名已存在，请重新输入");
            }
            userDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(String username) {
        // 安全性: 仅限网站管理人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        try {
            UserPO po = userDataService.get(username);
            userDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    public ResultMessage modifyBasicInfo(UserVO vo) {
        // 安全性: 仅限客户修改自己的基本信息
        if (session != null) {
            if (session.userType.equals(UserType.CUSTOMER)) {
                vo.username = session.username;
            } else {
                return null;
            }
        }

        if (vo.name.trim().isEmpty() || vo.contact.trim().isEmpty()) {
            return new ResultMessage(ResultMessage.RESULT_DATA_INVALID);
        }

        try {
            UserPO user = userDataService.get(vo.username);
            user.setName(vo.name);
            user.setContact(vo.contact);
            userDataService.update(user);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(UserVO vo) {
        // 安全性: 仅限网站管理人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MASTER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        try {
            UserPO newContent = UserBL.toPO(vo);
            userDataService.update(newContent);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            Logger.log(e);
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private static UserVO toVO(UserPO po) {
        return new UserVO(po.getUsername(), po.getPassword(), po.getName(), po.getContact(), po.getType());
    }

    private static UserPO toPO(UserVO vo) {
        if (vo.type == UserType.CUSTOMER) {
            MemberVO memberVO = new CustomerBL().getMemberInfo(vo.username);
            if (memberVO != null) {
                return new UserPO(vo.username, vo.password, vo.name, vo.contact, vo.type, memberVO.memberType, memberVO.birthday, memberVO.companyName);
            }
        }
        return new UserPO(vo.username, vo.password, vo.name, vo.contact, vo.type, MemberType.NONE, null, null);
    }
}
