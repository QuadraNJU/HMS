package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.hotelBL.HotelBLService;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelBLService_Driver {
    public void drive(HotelBLService hotelBLService) {
        try {
            ArrayList<HotelVO> list = hotelBLService.getAll();
            hotelBLService.search(1);
            hotelBLService.delete(list.get(0).id);
            hotelBLService.add(list.get(0));
            hotelBLService.modify(list.get(0).id, list.get(0));
            hotelBLService.changeStaff(list.get(0).id, "quadra");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
