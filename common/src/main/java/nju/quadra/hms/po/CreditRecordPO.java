package nju.quadra.hms.po;

import nju.quadra.hms.model.CreditAction;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordPO {
    /**
     * 信用记录ID
     */
    private int id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 发生时间
     */
    private long timestamp;
    /**
     * 相关订单ID
     */
    private int orderId;
    /**
     * 动作
     */
    private CreditAction action;
    /**
     * 信用度变化
     */
    private double diff;

    public CreditRecordPO(int id, String username, long timestamp, int orderId, CreditAction action, double diff) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.orderId = orderId;
        this.action = action;
        this.diff = diff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public CreditAction getAction() {
        return action;
    }

    public void setAction(CreditAction action) {
        this.action = action;
    }

    public double getDiff() {
        return diff;
    }

    public void setDiff(double diff) {
        this.diff = diff;
    }
}
