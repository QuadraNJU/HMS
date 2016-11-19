package nju.quadra.hms.vo;

import nju.quadra.hms.po.OrderPO;

/**
 * Created by adn55 on 16/10/15.
 * 用于在酒店详细信息中展示评价
 */
public class OrderRankVO {
    /**
     * 订单ID
     */
    public int orderId;
    /**
     * 评分
     */
    public int rank;
    /**
     * 评价内容
     */
    public String comment;

    public OrderRankVO(int orderId, int rank, String comment) {
        this.orderId = orderId;
        this.rank = rank;
        this.comment = comment;
    }

    public OrderRankVO(OrderPO po) {
        this(po.getId(), po.getRank(), po.getComment());
    }
}
