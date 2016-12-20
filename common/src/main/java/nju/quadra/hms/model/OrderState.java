package nju.quadra.hms.model;

public enum OrderState {
    BOOKED("未入住"),
    UNFINISHED("已入住"),
    FINISHED("已完成"),
    RANKED("已完成并评价"),
    DELAYED("异常(逾期)"),
    UNDO("已撤销");

    final String showname;

    OrderState(String showname) {
        this.showname = showname;
    }

    @Override
    public String toString() {
        return showname;
    }

    public static OrderState getById(int id) {
        return OrderState.values()[id];
    }
}
