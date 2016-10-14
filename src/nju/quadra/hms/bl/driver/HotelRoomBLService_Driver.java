package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.hotelRoomBL.HotelRoomBLService;
import nju.quadra.hms.vo.HotelRoomVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelRoomBLService_Driver {
    public void drive(HotelRoomBLService hotelRoomBLService) {
        try {
            ArrayList<HotelRoomVO> list = hotelRoomBLService.getAll(1);
            hotelRoomBLService.delete(list.get(0).id);
            hotelRoomBLService.add(list.get(0));
            hotelRoomBLService.modify(list.get(0).id, list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
