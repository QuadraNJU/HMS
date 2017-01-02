package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;

/**
 * 负责订单管理的数据层服务
 */
public interface OrderDataService {
    /**
     * 根据用户名查找并获得OrderPO对象
     *
     * @param username 用户名
     * @return 订单实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<OrderPO> getByCustomer(String username) throws Exception;

    /**
     * 根据酒店ID查找并获得OrderPO对象
     *
     * @param hotelId 酒店编号
     * @return 订单实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<OrderPO> getByHotel(int hotelId) throws Exception;

    /**
     * 根据订单状态查找并获得OrderPO对象
     *
     * @param state 订单状态
     * @return 订单实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<OrderPO> getByState(OrderState state) throws Exception;

    /**
     * 获得对应订单编号订单信息
     *
     * @param id 订单id
     * @return 订单信息实例化对象
     * @throws Exception 数据库访问异常
     */
    OrderPO getById(int id) throws Exception;

    /**
     * 在数据库中插入OrderPO对象
     *
     * @param po 订单实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(OrderPO po) throws Exception;

    /**
     * 在数据库中更新OrderPO对象
     *
     * @param po 订单实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(OrderPO po) throws Exception;

    /**
     * 在数据库中插入OrderPO对象
     *
     * @param po 订单实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(OrderPO po) throws Exception;
}
