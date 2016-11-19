package nju.quadra.hms.vo;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;
import java.util.Date;

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
     * 合作企业客户列表
     */
    public ArrayList<String> cooperation;

    public HotelPromotionVO(int id, int hotelId, String name, HotelPromotionType type, Date startTime, Date endTime, double promotion, ArrayList<String> cooperation) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.promotion = promotion;
        this.cooperation = cooperation;
    }

    public HotelPromotionVO(HotelPromotionPO po) {
        this(po.getId(), po.getHotelId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), new Gson().fromJson(po.getCooperation(), new TypeToken<ArrayList<String>>() {}.getType()));
    }

//    @Override
//    public boolean equals(Object obj) {
//        if(obj instanceof HotelPromotionVO) {
//            HotelPromotionVO hpvo = (HotelPromotionVO) obj;
//            if(id == hpvo.id && hotelId == hpvo.id && name.equals(hpvo.name)
//                    && type.equals(hpvo.type) && startTime.equals(hpvo.startTime)
//                    && endTime.equals(hpvo.endTime) && promotion == hpvo.promotion
//                    && cooperation.equals(hpvo.cooperation)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
