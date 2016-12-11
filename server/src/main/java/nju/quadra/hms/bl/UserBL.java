package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/18.
 */
public class UserBL implements UserBLService {
    private final UserDataService userDataService;

    public UserBL() {
        userDataService = new UserDataServiceImpl();
    }

    @Override
    public ResultMessage login(String username, String password) {
        try {
            UserPO po = userDataService.get(username);
            if (po != null && password.equals(po.getPassword())) {
                return new ResultMessage(ResultMessage.RESULT_SUCCESS);
            }
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
        return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "用户名或密码错误，请重新输入");
    }


    @Override
    public ArrayList<UserVO> getAll() {
        ArrayList<UserVO> voarr = new ArrayList<>();
        try {
            ArrayList<UserPO> poarr = userDataService.getAll();
            for (UserPO po : poarr) {
                voarr.add(UserBL.toVO(po));
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public UserVO get(String username) {
        try {
            UserPO po = userDataService.get(username);
            return UserBL.toVO(po);
        } catch (Exception e) {
            // e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage add(UserVO vo) {
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
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(String username) {
        try {
            UserPO po = userDataService.get(username);
            userDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    public ResultMessage modifyBasicInfo(UserVO vo) {
        try {
            UserPO user = userDataService.get(vo.username);
            user.setName(vo.name);
            user.setContact(vo.contact);
            userDataService.update(user);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(UserVO vo) {
        try {
            UserPO newContent = UserBL.toPO(vo);
            userDataService.update(newContent);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
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
