package nju.quadra.hms.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.po.WebsitePromotionPO;

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

    public WebsitePromotionVO(WebsitePromotionPO po) {
        this(po.getId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), po.getAreaId(), new Gson().fromJson(po.getMemberLevel(), new TypeToken<ArrayList<int[]>>() {}.getType()));
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof WebsitePromotionVO) {
//            WebsitePromotionVO wpvo = (WebsitePromotionVO) obj;
//            if(id == wpvo.id && name.equals(wpvo.name) && areaId == wpvo.areaId
//                    && type.equals(wpvo.type) && startTime.equals(wpvo.startTime)
//                    && endTime.equals(wpvo.endTime) && promotion == wpvo.promotion
//                    && memberLevel.equals(wpvo.memberLevel)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
