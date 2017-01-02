package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * 负责实现用户管理所需要的服务
 */
public interface UserBLService {
    /**
     * 用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @return 结果消息
     */
    ResultMessage login(String username, String password);

    /**
     * 获得所有用户账号信息
     *
     * @return 所用用户信息值对象集合
     */
    ArrayList<UserVO> getAll();

    /**
     * 获得用户信息
     *
     * @param username 用户名
     * @return 用户信息值对象
     */
    UserVO get(String username);

    /**
     * 添加用户信息
     *
     * @param vo 用户信息值对象
     * @return 结果消息
     */
    ResultMessage add(UserVO vo);

    /**
     * 删除用户信息
     *
     * @param username 用户名
     * @return 结果消息
     */
    ResultMessage delete(String username);

    /**
     * 用户修改自身信息
     *
     * @param vo 用户信息值对象
     * @return 结果消息
     */
    ResultMessage modifyBasicInfo(UserVO vo);

    /**
     * 修改用户信息
     *
     * @param vo 用户信息值对象
     * @return 结果消息
     */
    ResultMessage modify(UserVO vo);
}
