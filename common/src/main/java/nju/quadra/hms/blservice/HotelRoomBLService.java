package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * 负责实现客房信息管理所需要的服务
 */
public interface HotelRoomBLService {
    /**
     * 获得酒店客房列表集合
     *
     * @param hotelId 酒店编号
     * @return 酒店客房信息值对象的集合
     */
    ArrayList<HotelRoomVO> getAll(int hotelId);

    /**
     * 获得酒店客房信息
     *
     * @param roomId 客房编号
     * @return 客房信息值对象
     */
    HotelRoomVO getById(int roomId);

    /**
     * 添加酒店客房信息
     *
     * @param vo 酒店客房信息值对象
     * @return 结果消息
     */
    ResultMessage add(HotelRoomVO vo);

    /**
     * 删除酒店客房信息
     *
     * @param roomId 酒店客房信息编号
     * @return 结果消息
     */
    ResultMessage delete(int roomId);

    /**
     * 修改酒店客房信息
     *
     * @param vo 酒店客房信息值对象
     * @return 结果消息
     */
    ResultMessage modify(HotelRoomVO vo);
}
