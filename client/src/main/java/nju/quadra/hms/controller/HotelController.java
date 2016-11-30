package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class HotelController {
    private HttpRemote hotelRemote;

    public HotelController() {
        this.hotelRemote = new HttpRemote("HotelBL");
    }

    public ArrayList<HotelVO> search(int areaId) {
        try {
            return hotelRemote.invoke(new TypeToken<ArrayList<HotelVO>>(){}.getType(), "search", areaId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HotelVO> getAll() {
        try {
            return hotelRemote.invoke(new TypeToken<ArrayList<HotelVO>>(){}.getType(), "getAll");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getDetail(int id) {
        try {
            return hotelRemote.invoke(HotelVO.class, "getDetail", id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(HotelVO vo) {
        try {
            return hotelRemote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage delete(int id) {
        try {
            return hotelRemote.invoke(ResultMessage.class, "delete", id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modify(int id, HotelVO vo) {
        try {
            return hotelRemote.invoke(ResultMessage.class, "modify", id, vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage changeStaff(int id, String username) {
        try {
            return hotelRemote.invoke(ResultMessage.class, "changeStaff", id, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
