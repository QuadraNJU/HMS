package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.WebsitePromotionBLService;
import nju.quadra.hms.data.mysql.WebsitePromotionDataServiceImpl;
import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.LoginSession;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.WebsitePromotionPO;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by RaUkonn on 2016/11/21.
 */
public class WebsitePromotionBL implements WebsitePromotionBLService {
    private final LoginSession session;
    private final WebsitePromotionDataService websitePromotionDataService = new WebsitePromotionDataServiceImpl();

    public WebsitePromotionBL() {
        session = null;
    }

    public WebsitePromotionBL(LoginSession session) {
        this.session = session;
    }

    @Override
    public ArrayList<WebsitePromotionVO> get() {
        // 安全性: 仅限网站营销人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MARKETER)) {
            return new ArrayList<>();
        }

        ArrayList<WebsitePromotionVO> voarr = new ArrayList<>();
        try {
            ArrayList<WebsitePromotionPO> poarr = websitePromotionDataService.getAll();
            for(WebsitePromotionPO po: poarr) voarr.add(WebsitePromotionBL.toVO(po));
        } catch (Exception e) {
            // e.printStackTrace();
        }

        return voarr;
    }

    @Override
    public ResultMessage add(WebsitePromotionVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        // 安全性: 仅限网站营销人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MARKETER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        WebsitePromotionPO po = WebsitePromotionBL.toPO(vo);
        try {
            websitePromotionDataService.insert(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage delete(int promotionId) {
        // 安全性: 仅限网站营销人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MARKETER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        try {
            WebsitePromotionPO po = websitePromotionDataService.getById(promotionId);
            websitePromotionDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(WebsitePromotionVO vo) {
        ResultMessage checkResult = checkVO(vo);
        if (checkResult.result != ResultMessage.RESULT_SUCCESS) {
            return checkResult;
        }

        // 安全性: 仅限网站营销人员访问
        if (session != null && !session.userType.equals(UserType.WEBSITE_MARKETER)) {
            return new ResultMessage(ResultMessage.RESULT_ACCESS_DENIED);
        }

        WebsitePromotionPO po = WebsitePromotionBL.toPO(vo);
        try {
            websitePromotionDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private ResultMessage checkVO(WebsitePromotionVO vo) {
        if (vo.name.trim().isEmpty()) {
            return new ResultMessage("促销策略名称不能为空，请重新输入");
        }
        if (vo.promotion < 0 || vo.promotion > 1) {
            return new ResultMessage("折扣幅度应为 0~1 之间的小数，请重新输入");
        }
        if (vo.startTime.compareTo(vo.endTime) > 0) {
            return new ResultMessage("促销开始时间应不晚于结束时间，请重新输入");
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    private static WebsitePromotionVO toVO(WebsitePromotionPO po) {
        return new WebsitePromotionVO(po.getId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), po.getAreaId(), new Gson().fromJson(po.getMemberLevel(), new TypeToken<HashMap<Double, Double>>(){}.getType()));
    }

    private static WebsitePromotionPO toPO(WebsitePromotionVO vo) {
        return new WebsitePromotionPO(vo.id, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, vo.areaId, new Gson().toJson(vo.memberLevel));
    }
}
