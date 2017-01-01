package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * 负责实现酒店促销策略管理所需要的服务
 */
public interface HotelPromotionBLService {
    /**
     * 获得所有酒店促销策略
     *
     * @param hotelId 酒店编号
     * @return 酒店促销策略值对象的集合
     */
    ArrayList<HotelPromotionVO> get(int hotelId);

    /**
     * 添加酒店促销策略
     *
     * @param vo 酒店促销策略值对象
     * @return 结果消息
     */
    ResultMessage add(HotelPromotionVO vo);

    /**
     * 删除酒店促销策略
     *
     * @param promotionId 酒店促销策略编号
     * @return 结果消息
     */
    ResultMessage delete(int promotionId);

    /**
     * 修改酒店促销策略
     *
     * @param vo 酒店促销策略值对象
     * @return 结果消息
     */
    ResultMessage modify(HotelPromotionVO vo);
}
