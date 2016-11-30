package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.WebsitePromotionBLService;
import nju.quadra.hms.data.mysql.WebsitePromotionDataServiceImpl;
import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.WebsitePromotionPO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/21.
 */
public class WebsitePromotionBL implements WebsitePromotionBLService {
    WebsitePromotionDataService websitePromotionDataService;

    public WebsitePromotionBL() {
        websitePromotionDataService = new WebsitePromotionDataServiceImpl();
    }

    @Override
    public ArrayList<WebsitePromotionVO> get() {
        ArrayList<WebsitePromotionVO> voarr = new ArrayList<>();
        try {
            ArrayList<WebsitePromotionPO> poarr = websitePromotionDataService.getAll();
            for(WebsitePromotionPO po: poarr) voarr.add(WebsitePromotionBL.toVO(po));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return voarr;
    }

    @Override
    public ResultMessage add(WebsitePromotionVO vo) {
        WebsitePromotionPO po = WebsitePromotionBL.toPO(vo);
        try {
            websitePromotionDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(int promotionId) {
        try {
            WebsitePromotionPO po = websitePromotionDataService.getById(promotionId);
            websitePromotionDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(WebsitePromotionVO vo) {
        WebsitePromotionPO po = WebsitePromotionBL.toPO(vo);
        try {
            websitePromotionDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    public static WebsitePromotionVO toVO(WebsitePromotionPO po) {
        return new WebsitePromotionVO(po.getId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), po.getAreaId(), new Gson().fromJson(po.getMemberLevel(), new TypeToken<ArrayList<int[]>>() {}.getType()));
    }

    public static WebsitePromotionPO toPO(WebsitePromotionVO vo) {
        return new WebsitePromotionPO(vo.id, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, vo.areaId, new Gson().toJson(vo.memberLevel));
    }
}
