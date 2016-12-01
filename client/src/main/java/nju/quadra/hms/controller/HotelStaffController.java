package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/30.
 */
public class HotelStaffController {

    private HotelBLService hotelBL = BLServiceFactory.getHotelBLService();
    private HotelRoomBLService hotelRoomBL = BLServiceFactory.getHotelRoomBLService();

    private HotelVO hotelVO;

    public HotelStaffController(String username) {
        hotelVO = hotelBL.getByStaff(username);
    }

    public ArrayList<AreaVO> getAllArea() {
        try {
            return hotelBL.getAllArea();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getHotelInfo() {
        try {
            return hotelVO;
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

    public ArrayList<HotelRoomVO> getHotelRooms() {
        try {
            return hotelRoomBL.getAll(hotelVO.id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addHotelRoom(HotelRoomVO vo) {
        vo.hotelId = this.hotelVO.id;
        try {
            return hotelRoomBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modifyHotelRoom(HotelRoomVO vo) {
        try {
            return hotelRoomBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage deleteHotelRoom(int id) {
        try {
            return hotelRoomBL.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
