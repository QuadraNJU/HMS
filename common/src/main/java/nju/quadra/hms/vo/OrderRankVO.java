package nju.quadra.hms.vo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderRankVO {
    /**
     * 订单ID
     */
    public int orderId;
    /**
     * 评价时间
     */
    public LocalDate date;
    /**
     * 评分
     */
    public int rank;
    /**
     * 评价内容
     */
    public String comment;

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
