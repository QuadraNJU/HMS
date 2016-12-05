package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.blservice.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelRoomController {
    private HotelRoomBLService hotelRoomBL;

    public HotelRoomController() {
        this.hotelRoomBL = BLServiceFactory.getHotelRoomBLService();
    }

    public ArrayList<HotelRoomVO> getAll(int hotelId) {
        try {
            return hotelRoomBL.getAll(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(HotelRoomVO vo) {
        try {
            return hotelRoomBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int roomId) {
        try {
            return hotelRoomBL.delete(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(HotelRoomVO vo) {
        try {
            return hotelRoomBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
