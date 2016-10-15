package nju.quadra.hms.model;

/**
 * Created by adn55 on 16/10/15.
 */
public enum OrderState {
    UNCOMPLETED,
    FINISHED,
    DELAYED,
    UNDO,
    RANKED;

    @Override
    public String toString() {
        switch (this) {
            case UNCOMPLETED:
                return "未执行";
            case FINISHED:
                return "已完成";
            case DELAYED:
                return "异常(逾期)";
            case UNDO:
                return "已撤销";
            case RANKED:
                return "已完成并评价";
            default:
                return null;
        }
    }
}
