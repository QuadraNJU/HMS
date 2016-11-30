package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelRoomController {
    private HttpRemote hotelRoomRemote;

    public HotelRoomController() {
        this.hotelRoomRemote = new HttpRemote("HotelRoomBL");
    }

    public ArrayList<HotelRoomVO> getAll(int hotelId) {
        try {
            return hotelRoomRemote.invoke(new TypeToken<ArrayList<HotelRoomVO>>(){}.getType(), "getAll", hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(HotelRoomVO vo) {
        try {
            return hotelRoomRemote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int roomId) {
        try {
            return hotelRoomRemote.invoke(ResultMessage.class, "delete", roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(HotelRoomVO vo) {
        try {
            return hotelRoomRemote.invoke(ResultMessage.class, "modify", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
