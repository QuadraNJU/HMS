package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;

import java.util.ArrayList;

public interface HotelRoomBLService {
    ArrayList<HotelRoomVO> getAll(int hotelId);

    HotelRoomVO getById(int roomId);

    ResultMessage add(HotelRoomVO vo);

    ResultMessage delete(int roomId);

    ResultMessage modify(HotelRoomVO vo);
}
