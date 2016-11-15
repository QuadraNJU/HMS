package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.po.HotelRoomPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelRoomDataService_Driver {
    public void drive(HotelRoomDataService hotelRoomDataService) {
        try {
            ArrayList<HotelRoomPO> list = hotelRoomDataService.get(1);
            hotelRoomDataService.delete(list.get(0));
            hotelRoomDataService.insert(list.get(0));
            hotelRoomDataService.update(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
