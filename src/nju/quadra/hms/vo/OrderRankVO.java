package nju.quadra.hms.vo;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderRankVO {
    /**
     * 评分
     */
    public int rank;
    /**
     * 评价内容
     */
    public String comment;

    public OrderRankVO(int rank, String comment) {
        this.rank = rank;
        this.comment = comment;
    }
}
