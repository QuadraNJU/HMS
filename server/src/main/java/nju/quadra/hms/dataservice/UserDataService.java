package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.UserPO;

import java.util.ArrayList;

/**
 * 负责用户信息管理的数据层服务
 */
public interface UserDataService {
    /**
     * 从数据库中获得UserPO对象
     *
     * @return 用户实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<UserPO> getAll() throws Exception;

    /**
     * 根据用户名查找并获得UserPO对象
     *
     * @param username 用户名
     * @return 用户实例化对象
     * @throws Exception 数据库访问异常
     */
    UserPO get(String username) throws Exception;

    /**
     * 在数据库中插入UserPO对象
     *
     * @param po 用户实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(UserPO po) throws Exception;

    /**
     * 在数据库中删除UserPO对象
     *
     * @param po 用户实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(UserPO po) throws Exception;

    /**
     * 在数据库中更新UserPO对象
     *
     * @param po 用户实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(UserPO po) throws Exception;
}
