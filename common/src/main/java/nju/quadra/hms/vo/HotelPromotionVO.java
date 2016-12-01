package nju.quadra.hms.vo;

import nju.quadra.hms.model.HotelPromotionType;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelPromotionVO {
    /**
     * 酒店促销策略ID
     */
    public int id;
    /**
     * 酒店ID
     */
    public int hotelId;
    /**
     * 名称
     */
    public String name;
    /**
     * 优惠类型
     */
    public HotelPromotionType type;
    /**
     * 起始时间
     */
    public LocalDate startTime;
    /**
     * 终止时间
     */
    public LocalDate endTime;
    /**
     * 折扣力度
     */
    public double promotion;
    /**
     * 合作企业客户列表
     */
    public ArrayList<String> cooperation;

    public HotelPromotionVO(int id, int hotelId, String name, HotelPromotionType type, LocalDate startTime, LocalDate endTime, double promotion, ArrayList<String> cooperation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promotion = promotion;
        this.cooperation = cooperation;
    }

}
