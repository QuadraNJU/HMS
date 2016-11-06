package nju.quadra.hms.bl.mockObject;

import nju.quadra.hms.blservice.hotelBL.HotelBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class MockHotelBL implements HotelBLService {
    private ArrayList<HotelVO> list;
    private HotelVO vo;

    public MockHotelBL() {
        list = new ArrayList<>();
        vo = new HotelVO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        list.add(vo);
    }

    @Override
    public ArrayList<HotelVO> search(int areaId) {
        return list;
    }

    @Override
    public ArrayList<HotelVO> getAll() {
        return list;
    }

    @Override
    public HotelVO getDetail(int id) {
        return vo;
    }

    @Override
    public ResultMessage add(HotelVO vo) {
        for(HotelVO currHotel: list) {
            if(currHotel.id == vo.id) return new ResultMessage(ResultMessage.RESULT_ERROR);
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(int id) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage modify(int id, HotelVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage changeStaff(int id, String username) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
