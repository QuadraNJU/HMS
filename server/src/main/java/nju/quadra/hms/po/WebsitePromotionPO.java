package nju.quadra.hms.po;

import nju.quadra.hms.model.WebsitePromotionType;

import java.time.LocalDate;

public class WebsitePromotionPO implements Comparable<WebsitePromotionPO>{
    /**
     * 网站促销策略ID
     */
    private int id;
    /**
     * 名称
     */
    private String name;
    /**
     * 优惠类型
     */
    private WebsitePromotionType type;
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
     * 商圈ID
     */
    private int areaId;
    /**
     * 会员等级规则
     */
    private String memberLevel;

    public WebsitePromotionPO(int id, String name, WebsitePromotionType type, LocalDate startTime, LocalDate endTime, double promotion, int areaId, String memberLevel) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promotion = promotion;
        this.areaId = areaId;
        this.memberLevel = memberLevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WebsitePromotionType getType() {
        return type;
    }

    public void setType(WebsitePromotionType type) {
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

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    @Override
    public int compareTo(WebsitePromotionPO o) {
        if(promotion < o.getPromotion()) return -1;
        else if(promotion == o.getPromotion()) return 0;
        else return 1;
    }
}
