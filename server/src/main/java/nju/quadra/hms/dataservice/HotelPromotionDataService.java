package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;

/**
 * 负责酒店促销策略管理的数据层服务
 */
public interface HotelPromotionDataService {
    /**
     * 根据酒店编号查找并获得所有酒店促销策略
     *
     * @param hotelId 酒店编号
     * @return 酒店促销策略实例化对象集合
     * @throws Exception 访问数据库异常
     */
    ArrayList<HotelPromotionPO> get(int hotelId) throws Exception;

    /**
     * 获得对应编号的酒店促销策略
     *
     * @param promotionId 促销策略编号
     * @return 酒店促销策略实例化对象集合
     * @throws Exception 访问数据库异常
     */
    HotelPromotionPO getById(int promotionId) throws Exception;

    /**
     * 在数据库中插入HotelPromotionPO对象
     *
     * @param po 酒店促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(HotelPromotionPO po) throws Exception;

    /**
     * 在数据库中删除HotelPromotionPO对象
     *
     * @param po 酒店促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(HotelPromotionPO po) throws Exception;

    /**
     * 在数据库中更新HotelPromotionPO对象
     *
     * @param po 酒店促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(HotelPromotionPO po) throws Exception;
}
