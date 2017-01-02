package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.CreditRecordPO;

import java.util.ArrayList;

/**
 * 负责实现信用记录和信用值管理的数据层服务
 */
public interface CreditDataService {
    /**
     * 根据用户名查找并获得CreditRecordPO对象列表
     *
     * @param username 用户名
     * @return 信用记录信息实例化对象的集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<CreditRecordPO> get(String username) throws Exception;

    /**
     * 在数据库中插入CreditRecordPO对象
     *
     * @param po 用户信用信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(CreditRecordPO po) throws Exception;
}
