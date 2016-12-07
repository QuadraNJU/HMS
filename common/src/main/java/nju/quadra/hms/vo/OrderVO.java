package nju.quadra.hms.vo;

import nju.quadra.hms.model.OrderState;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderVO {
    /**
     * 订单ID
     */
    public int id;
    /**
     * 用户名
     */
    public String username;
    /**
     * 酒店ID
     */
    public int hotelId;
    /**
     * 起始时间
     */
    public LocalDate startDate;
    /**
     * 终止时间
     */
    public LocalDate endDate;
    /**
     * 客房ID
     */
    public int roomId;
    /**
     * 客房数量
     */
    public int roomCount;
    /**
     * 入住人数
     */
    public int personCount;
    /**
     * 入住人员列表
     */
    public ArrayList<String> persons;
    /**
     * 有无儿童
     */
    public boolean hasChildren;
    /**
     * 订单价格
     */
    public double price;
    /**
     * 订单状态
     */
    public OrderState state;
    /**
     * 评分
     */
    public int rank;
    /**
     * 评价内容
     */
    public String comment;

    public OrderVO(int id, String username, int hotelId, LocalDate startDate, LocalDate endDate, int roomId, int roomCount, int personCount, ArrayList<String> persons, boolean hasChildren, double price, OrderState state, int rank, String comment) {
        this.id = id;
        this.username = username;
        this.hotelId = hotelId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomId = roomId;
        this.roomCount = roomCount;
        this.personCount = personCount;
        this.persons = persons;
        this.hasChildren = hasChildren;
        this.price = price;
        this.state = state;
        this.rank = rank;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return startDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd - "))
                + endDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd "))
                + "\n" + state.toString();
    }

    public String printRank() {
        String stars = "";
        for (int i = 0; i < rank; i++) {
            stars += "★";
        }
        for (int i = 0; i < 5-rank; i++) {
            stars += "☆";
        }
        return endDate.format(DateTimeFormatter.ofPattern("uuuu/MM/dd ")) + stars + "\n" + comment;
    }

    public String printPersons() {
        StringBuilder sb = new StringBuilder();
        for(String s: persons) sb.append(s + "、");
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
