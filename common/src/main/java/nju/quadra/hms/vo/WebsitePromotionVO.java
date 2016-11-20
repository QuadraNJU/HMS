package nju.quadra.hms.vo;

import nju.quadra.hms.model.WebsitePromotionType;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class WebsitePromotionVO {
    /**
     * 网站促销策略ID
     */
    public int id;
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
    public Date startTime;
    /**
     * 终止时间
     */
    public Date endTime;
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
    public ArrayList<int[]> memberLevel;

    public WebsitePromotionVO(int id, String name, WebsitePromotionType type, Date startTime, Date endTime, double promotion, int areaId, ArrayList<int[]> memberLevel) {
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
