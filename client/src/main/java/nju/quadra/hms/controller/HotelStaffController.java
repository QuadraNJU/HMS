package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelStaffController {

    private HotelBLService hotelBL = BLServiceFactory.getHotelBLService();

    public ArrayList<AreaVO> getAllArea() {
        try {
            return hotelBL.getAllArea();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getHotelInfo(String username) {
        try {
            return hotelBL.getByStaff(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modifyHotelInfo(HotelVO vo) {
        try {
            return hotelBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
