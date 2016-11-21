package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.promotionBL.WebsitePromotionBLService;
import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.WebsitePromotionPO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/21.
 */
//todo:空方法
public class WebsitePromotionBL implements WebsitePromotionBLService {
    @Override
    public ArrayList<WebsitePromotionVO> get() {
        return null;
    }

    @Override
    public ResultMessage add(WebsitePromotionVO vo) {
        return null;
    }

    @Override
    public ResultMessage delete(int promotionId) {
        return null;
    }

    @Override
    public ResultMessage modify(int promotionId, WebsitePromotionVO vo) {
        return null;
    }

    public static WebsitePromotionVO toVO(WebsitePromotionPO po) {
        return new WebsitePromotionVO(po.getId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), po.getAreaId(), new Gson().fromJson(po.getMemberLevel(), new TypeToken<ArrayList<int[]>>() {}.getType()));
    }

    public static WebsitePromotionPO toPO(WebsitePromotionVO vo) {
        return new WebsitePromotionPO(vo.id, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, vo.areaId, new Gson().toJson(vo.memberLevel));
    }
}
