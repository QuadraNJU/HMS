package nju.quadra.hms.bl.mockObject;

import nju.quadra.hms.blservice.HotelPromotionBLService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class MockHotelPromotionBL implements HotelPromotionBLService {
    @Override
    public ArrayList<HotelPromotionVO> get(int hotelId) {
        ArrayList<HotelPromotionVO> list = new ArrayList<>();
        HotelPromotionVO vo = new HotelPromotionVO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        list.add(vo);
        return list;
    }

    @Override
    public ResultMessage add(HotelPromotionVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(int promotionId) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage modify(HotelPromotionVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
