package nju.quadra.hms.vo;

import nju.quadra.hms.data.mysql.CreditDataServiceImpl;
import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.po.CreditRecordPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordVO {
    /**
     * 每人初始有100额度的信用
     */
    private int INIT_CREDIT_RESULT = 100;

    /**
     * 信用记录ID
     */
    public int id;
    /**
     * 用户名
     */
    public String username;
    /**
     * 发生时间
     */
    public Date timestamp;
    /**
     * 相关订单ID
     */
    public int orderId;
    /**
     * 动作
     */
    public CreditAction action;
    /**
     * 信用度变化
     */
    public double diff;
    /**
     * 信用度结果
     */
    public double creditResult;

    public CreditRecordVO(int id, String username, Date timestamp, int orderId, CreditAction action, double diff, double creditResult) {
        this.id = id;
        this.username = username;
        this.timestamp = timestamp;
        this.orderId = orderId;
        this.action = action;
        this.diff = diff;
        this.creditResult = creditResult;
    }

    public CreditRecordVO(CreditRecordPO po) {
        this(po.getId(), po.getUsername(), po.getTimestamp(), po.getOrderId(), po.getAction(), po.getDiff(), 0);

        CreditDataService creditDataService = new CreditDataServiceImpl();
        double creditResult = INIT_CREDIT_RESULT;
        try {
            ArrayList<CreditRecordPO> poarr = creditDataService.get(po.getUsername());
            for(CreditRecordPO po1: poarr) creditResult += po1.getDiff();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.creditResult = creditResult;
    }
}
