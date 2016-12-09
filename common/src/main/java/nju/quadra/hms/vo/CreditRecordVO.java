package nju.quadra.hms.vo;

import nju.quadra.hms.model.CreditAction;

import java.time.LocalDateTime;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordVO {
    /**
     * 信用记录ID
     */
    private final int id;
    /**
     * 用户名
     */
    public final String username;
    /**
     * 发生时间
     */
    public final LocalDateTime timestamp;
    /**
     * 相关订单ID
     */
    public final int orderId;
    /**
     * 动作
     */
    public final CreditAction action;
    /**
     * 信用度变化
     */
    public final double diff;
    /**
     * 信用度结果
     */
    public final double creditResult;

    public CreditRecordVO(int id, String username, LocalDateTime timestamp, int orderId, CreditAction action, double diff, double creditResult) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.orderId = orderId;
        this.action = action;
        this.diff = diff;
        this.creditResult = creditResult;
    }
}
