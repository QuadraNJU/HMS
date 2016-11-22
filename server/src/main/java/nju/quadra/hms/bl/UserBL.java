package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.data.mysql.UserDataServiceImpl;
import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.UserPO;
import nju.quadra.hms.vo.UserVO;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/18.
 */
public class UserBL implements UserBLService {
    private UserDataService userDataService;

    public UserBL() {
        userDataService = new UserDataServiceImpl();
    }

    @Override
    public ResultMessage login(String username, String password) {
        try {
            UserPO po = userDataService.get(username);
            if (password.equals(po.getPassword())) {
                return new ResultMessage(ResultMessage.RESULT_SUCCESS);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public UserVO get(String username) {
        UserPO po = null;
        try {
            po = userDataService.get(username);
            return UserBL.toVO(po);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResultMessage add(UserVO vo) {
        UserPO po = UserBL.toPO(vo);
        try {
            userDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (SQLIntegrityConstraintViolationException e) {
            return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "用户名已存在，请重新输入");
        } catch (Exception e) {
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    public static UserVO toVO(UserPO po) {
        return new UserVO(po.getUsername(), po.getPassword(), po.getName(), po.getContact(), po.getType(), po.getMemberType(), po.getBirthday(), po.getCompanyName());

    }

    public static UserPO toPO(UserVO vo) {
        return new UserPO(vo.username, vo.password, vo.name, vo.contact, vo.type, vo.memberType, vo.birthday, vo.companyName);
    }
}
