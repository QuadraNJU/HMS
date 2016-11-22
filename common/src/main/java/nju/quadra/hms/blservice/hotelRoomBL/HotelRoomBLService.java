package nju.quadra.hms.blservice.hotelRoomBL;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelRoomBLService {
    ArrayList<HotelRoomVO> getAll(int hotelId);

    ResultMessage add(HotelRoomVO vo);

    ResultMessage delete(int roomId);

    ResultMessage modify(HotelRoomVO vo);
}
