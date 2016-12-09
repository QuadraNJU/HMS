package nju.quadra.hms.vo;

import nju.quadra.hms.model.WebsitePromotionType;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Created by adn55 on 16/10/15.
 */
public class WebsitePromotionVO {
    /**
     * 网站促销策略ID
     */
    public final int id;
    /**
     * 名称
     */
    public String name;
    /**
     * 优惠类型
     */
    public WebsitePromotionType type;
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
     * 商圈ID
     */
    public int areaId;
    /**
     * 会员等级规则
     */
    public HashMap<Double, Double> memberLevel;

    public WebsitePromotionVO(int id, String name, WebsitePromotionType type, LocalDate startTime, LocalDate endTime, double promotion, int areaId, HashMap<Double, Double> memberLevel) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promotion = promotion;
        this.areaId = areaId;
        this.memberLevel = memberLevel;
    }
}
