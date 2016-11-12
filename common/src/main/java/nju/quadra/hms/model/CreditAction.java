package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum CreditAction {
    ORDER_FINISHED("完成订单"),
    ORDER_CANCELLED("撤销未执行订单"),
    ORDER_DELAYED("订单逾期未执行"),
    ORDER_UNDO("撤销异常订单返还"),
    CREDIT_TOPUP("信用充值");

    String showname;

    CreditAction(String showname) {
        this.showname = showname;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static CreditAction getById(int id) {
        return CreditAction.values()[id];
    }
}
