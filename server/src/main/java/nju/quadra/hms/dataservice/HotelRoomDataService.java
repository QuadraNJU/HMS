package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelRoomPO;

import java.util.ArrayList;

/**
 * 负责客房信息管理的数据层服务
 */
public interface HotelRoomDataService {
    /**
     * 获得某酒店是所有的客房信息
     *
     * @param hotelId 酒店编号
     * @return 客房信息实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<HotelRoomPO> get(int hotelId) throws Exception;

    /**
     * 在数据库中插入HotelRoomPO对象
     *
     * @param po 客房信息值对象
     * @throws Exception 数据库访问异常
     */
    void insert(HotelRoomPO po) throws Exception;

    /**
     * 根据客房编号查找并获得客房
     *
     * @param roomId 客房编号
     * @return 客房信息值对象
     * @throws Exception 数据库访问异常
     */
    HotelRoomPO getById(int roomId) throws Exception;

    /**
     * 在数据库中删除HotelRoomPO对象
     *
     * @param po 客房信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(HotelRoomPO po) throws Exception;

    /**
     * 在数据库中更新HotelRoomPO对象
     *
     * @param po 客房信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(HotelRoomPO po) throws Exception;
}
