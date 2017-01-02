package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.AreaPO;
import nju.quadra.hms.po.HotelPO;

import java.util.ArrayList;

/**
 * 负责酒店信息管理的数据层服务
 */
public interface HotelDataService {
    /**
     * 从数据库中获得HotelPO对象集合
     *
     * @return 酒店信息实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<HotelPO> getAll() throws Exception;

    /**
     * 根据酒店ID查找并获得HotelPO对象
     *
     * @param id 酒店编号
     * @return 酒店信息实例化对象
     * @throws Exception 数据库访问异常
     */
    HotelPO getById(int id) throws Exception;

    /**
     * 根据商圈ID查找并获得HotelPO对象
     *
     * @param areaId 地区编号
     * @return 酒店信息实例化对象的集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<HotelPO> getByArea(int areaId) throws Exception;

    /**
     * 根据工作人员用户名查找并获得HotelPO对象
     *
     * @param staff 工作人员用户名
     * @return 酒店信息实例化对象
     * @throws Exception 数据库访问异常
     */
    HotelPO getByStaff(String staff) throws Exception;

    /**
     * 获得所有地区实例化对象
     *
     * @return 地区实例化对象集合
     * @throws Exception 数据库访问异常
     */
    ArrayList<AreaPO> getAllArea() throws Exception;

    /**
     * 在数据库中插入HotelPO对象
     *
     * @param po 酒店信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(HotelPO po) throws Exception;

    /**
     * 在数据库中删除HotelPO对象
     *
     * @param po 酒店信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(HotelPO po) throws Exception;

    /**
     * 在数据库中更新HotelPO对象
     *
     * @param po 酒店信息实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(HotelPO po) throws Exception;
}
