package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.po.HotelPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelDataService_Driver {
    public void drive(HotelDataService hotelDataService) {
        try {
            ArrayList<HotelPO> list = hotelDataService.getAll();
            hotelDataService.selectByArea(1);
            hotelDataService.selectById(1);
            hotelDataService.delete(list.get(0));
            hotelDataService.insert(list.get(0));
            hotelDataService.update(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
