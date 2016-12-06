package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.CustomerBLService;
import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelVO;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/12/4.
 */
public class WebmasterController {

    private UserBLService userBL = BLServiceFactory.getUserBLService();
    private HotelBLService hotelBL = BLServiceFactory.getHotelBLService();
    private CustomerBLService customerBL = BLServiceFactory.getCustomerBLService();

    public ArrayList<UserVO> getAllUsers() {
        try {
            return userBL.getAll();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addUser(UserVO vo) {
        try {
            return userBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modifyUser(UserVO vo) {
        try {
            return userBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage deleteUser(String username) {
        try {
            return userBL.delete(username);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public MemberVO getMemberInfo(String username) {
        try {
            return customerBL.getMemberInfo(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modifyMemberInfo(MemberVO vo) {
        try {
            return customerBL.modifyMemberInfo(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public UserVO getUserInfo(String username) {
        try {
            return userBL.get(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<AreaVO> getAllArea() {
        try {
            return hotelBL.getAllArea();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<HotelVO> getHotelsByArea(int areaId) {
        try {
            return hotelBL.getByArea(areaId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public HotelVO getHotelByStaff(String username) {
        try {
            return hotelBL.getByStaff(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addHotel(HotelVO vo) {
        try {
            return hotelBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage modifyHotel(HotelVO vo) {
        try {
            return hotelBL.modify(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage deleteHotel(int hotelId) {
        try {
            return hotelBL.delete(hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
