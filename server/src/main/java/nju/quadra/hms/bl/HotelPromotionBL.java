package nju.quadra.hms.bl;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.HotelPromotionBLService;
import nju.quadra.hms.data.mysql.HotelPromotionDataServiceImpl;
import nju.quadra.hms.dataservice.HotelPromotionDataService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.HotelPromotionPO;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/21.
 */

public class HotelPromotionBL implements HotelPromotionBLService {
    HotelPromotionDataService hotelPromotionDataService;

    public HotelPromotionBL() {
        hotelPromotionDataService = new HotelPromotionDataServiceImpl();
    }

    @Override
    public ArrayList<HotelPromotionVO> get(int hotelId) {
        ArrayList<HotelPromotionVO> voarr = new ArrayList<>();
        try {
            ArrayList<HotelPromotionPO> poarr = hotelPromotionDataService.get(hotelId);
            for(HotelPromotionPO po: poarr) voarr.add(HotelPromotionBL.toVO(po));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ResultMessage add(HotelPromotionVO vo) {
        HotelPromotionPO po = HotelPromotionBL.toPO(vo);
        try {
            hotelPromotionDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(int promotionId) {
        try {
            HotelPromotionPO po = hotelPromotionDataService.getById(promotionId);
            hotelPromotionDataService.delete(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    @Override
    public ResultMessage modify(HotelPromotionVO vo) {
        HotelPromotionPO po = HotelPromotionBL.toPO(vo);
        try {
            hotelPromotionDataService.update(po);
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }

    private static HotelPromotionVO toVO(HotelPromotionPO po) {
        ArrayList<String> cooperation = null;
        if (po.getCooperation() != null && po.getType().equals(HotelPromotionType.COMPANY_PROMOTION)) {
            cooperation = new Gson().fromJson(po.getCooperation(), new TypeToken<ArrayList<String>>(){}.getType());
        }
        return new HotelPromotionVO(po.getId(), po.getHotelId(), po.getName(), po.getType(), po.getStartTime(), po.getEndTime(), po.getPromotion(), cooperation);
    }

    private static HotelPromotionPO toPO(HotelPromotionVO vo) {
        if (vo.type.equals(HotelPromotionType.COMPANY_PROMOTION)) {
            return new HotelPromotionPO(vo.id, vo.hotelId, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, new Gson().toJson(vo.cooperation));
        } else {
            return new HotelPromotionPO(vo.id, vo.hotelId, vo.name, vo.type, vo.startTime, vo.endTime, vo.promotion, null);
        }
    }
}
