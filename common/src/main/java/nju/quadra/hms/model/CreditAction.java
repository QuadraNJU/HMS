package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum CreditAction {
    ORDER_FINISHED,
    ORDER_CANCELLED,
    ORDER_DELAYED,
    ORDER_UNDO,
    CREDIT_TOPUP;

    @Override
    public String toString() {
        switch (this) {
            case ORDER_FINISHED:
                return "完成订单";
            case ORDER_CANCELLED:
                return "撤销未执行订单";
            case ORDER_DELAYED:
                return "订单逾期未执行";
            case ORDER_UNDO:
                return "撤销异常订单返还";
            case CREDIT_TOPUP:
                return "信用充值";
            default:
                return null;
        }
    }
}
