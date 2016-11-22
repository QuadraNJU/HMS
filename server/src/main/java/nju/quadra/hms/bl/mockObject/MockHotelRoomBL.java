package nju.quadra.hms.bl.mockObject;

import nju.quadra.hms.blservice.hotelRoomBL.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class MockHotelRoomBL implements HotelRoomBLService {
    @Override
    public ArrayList<HotelRoomVO> getAll(int hotelId) {
        ArrayList<HotelRoomVO> list = new ArrayList<>();
        HotelRoomVO vo = new HotelRoomVO(1, 1, "大床房", 69, 129.9);
        list.add(vo);
        return list;
    }

    @Override
    public ResultMessage add(HotelRoomVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage delete(int roomId) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage modify(HotelRoomVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
