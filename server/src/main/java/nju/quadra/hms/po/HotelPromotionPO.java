package nju.quadra.hms.po;

import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.vo.HotelRoomVO;

import java.time.LocalDate;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelPromotionPO implements Comparable<HotelPromotionPO>{
    /**
     * 酒店促销策略ID
     */
    private int id;
    /**
     * 酒店ID
     */
    private int hotelId;
    /**
     * 名称
     */
    private String name;
    /**
     * 优惠类型
     */
    private HotelPromotionType type;
    /**
     * 起始时间
     */
    private LocalDate startTime;
    /**
     * 终止时间
     */
    private LocalDate endTime;
    /**
     * 折扣力度
     */
    private double promotion;
    /**
     * 合作企业客户列表
     */
    private String cooperation;

    public HotelPromotionPO(int id, int hotelId, String name, HotelPromotionType type, LocalDate startTime, LocalDate endTime, double promotion, String cooperation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promotion = promotion;
        this.cooperation = cooperation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HotelPromotionType getType() {
        return type;
    }

    public void setType(HotelPromotionType type) {
        this.type = type;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDate startTime) {
        this.startTime = startTime;
    }

    public LocalDate getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDate endTime) {
        this.endTime = endTime;
    }

    public double getPromotion() {
        return promotion;
    }

    public void setPromotion(double promotion) {
        this.promotion = promotion;
    }

    public String getCooperation() {
        return cooperation;
    }

    public void setCooperation(String cooperation) {
        this.cooperation = cooperation;
    }

    @Override
    public int compareTo(HotelPromotionPO o) {
        if(promotion < o.getPromotion()) return -1;
        else if(promotion == o.getPromotion()) return 0;
        else return 1;
    }

}
