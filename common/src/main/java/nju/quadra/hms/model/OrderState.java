package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum OrderState {
    UNCOMPLETED("未执行"),
    FINISHED("已完成"),
    DELAYED("异常(逾期)"),
    UNDO("已撤销"),
    RANKED("已完成并评价");

    String showname;

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
