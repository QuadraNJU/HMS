package nju.quadra.hms.blservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.OrderDetailVO;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;
import nju.quadra.hms.vo.PriceVO;

import java.util.ArrayList;

/**
 * 负责实现订单管理所需要的服务
 */
public interface OrderBLService {
    /**
     * 获得订单价格
     *
     * @param vo 订单信息值对象
     * @return 价格信息值对象
     */
    PriceVO getPrice(OrderVO vo);

    /**
     * 添加订单
     *
     * @param vo 订单信息值对象
     * @return 结果消息
     */
    ResultMessage add(OrderVO vo);

    /**
     * 获得某个客户的所有订单具体信息
     *
     * @param username 用户名
     * @return 订单具体信息值对象的集合
     */
    ArrayList<OrderDetailVO> getByCustomer(String username);

    /**
     * 获得某个酒店所有订单具体信息
     *
     * @param hotelId 酒店编号
     * @return 订单具体信息值对象的集合
     */
    ArrayList<OrderDetailVO> getByHotel(int hotelId);

    /**
     * 获得某个订单状态所有订单具体信息
     *
     * @param state 订单状态
     * @return 订单具体信息值对象的集合
     */
    ArrayList<OrderDetailVO> getByState(OrderState state);

    /**
     * 撤销异常订单
     *
     * @param orderId         订单编号
     * @param returnAllCredit 是否恢复所有信用值
     * @return 结果消息
     */
    ResultMessage undoDelayed(int orderId, boolean returnAllCredit);

    /**
     * 撤销未执行订单
     *
     * @param orderId 订单编号
     * @return 结果消息
     */
    ResultMessage undoUnfinished(int orderId);

    /**
     * 订单入住办理
     *
     * @param orderId 订单编号
     * @return 结果消息
     */
    ResultMessage checkin(int orderId);

    /**
     * 订单退房办理
     *
     * @param orderId 订单编号
     * @return 结果消息
     */
    ResultMessage checkout(int orderId);

    /**
     * 添加订单评价
     *
     * @param vo 订单评价值对象
     * @return 结果消息
     */
    ResultMessage addRank(OrderRankVO vo);
}
