package nju.quadra.hms.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class OrderRankVO {
    /**
     * 订单ID
     */
    public final int orderId;
    /**
     * 评价时间
     */
    private final LocalDate date;
    /**
     * 评分
     */
    public final int rank;
    /**
     * 评价内容
     */
    public final String comment;

    public OrderRankVO(int orderId, LocalDate date, int rank, String comment) {
        this.orderId = orderId;
        this.date = date;
        this.rank = rank;
        this.comment = comment;
    }

    @Override
    public String toString() {
        String stars = "";
        for (int i = 0; i < rank; i++) {
            stars += "★";
        }
        for (int i = 0; i < 5-rank; i++) {
            stars += "☆";
        }
        return date.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")) + stars + "\n" + comment;
    }

}
