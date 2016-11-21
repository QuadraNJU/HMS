package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.promotionBL.HotelPromotionBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelPromotionPO;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/21.
 */

//todo:空方法
public class HotelPromotionBL implements HotelPromotionBLService {
    @Override
    public ArrayList<HotelPromotionVO> get(int hotelId) {
        return null;
    }

    @Override
    public ResultMessage add(HotelPromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage delete(int promotionId) {
        return null;
    }

    @Override
    public ResultMessage modify(int promotionId, HotelPromotionVO vo) {
        return null;
    }

    public static HotelPromotionVO toVO(HotelPromotionPO po) {
        return new HotelPromotionVO(po.getId(), po.getHotelId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), new Gson().fromJson(po.getCooperation(), new TypeToken<ArrayList<String>>() {}.getType()));
    }

    protected static HotelPromotionPO toPO(HotelPromotionVO vo) {
        return new HotelPromotionPO(vo.id, vo.hotelId, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, new Gson().toJson(vo.cooperation));
    }
}
