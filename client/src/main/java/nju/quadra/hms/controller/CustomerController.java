package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.blservice.CustomerBLService;
import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.*;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/11/25.
 */
public class CustomerController {

    private CustomerBLService customerBL = BLServiceFactory.getCustomerBLService();
    private CreditRecordBLService creditBL = BLServiceFactory.getCreditRecordBLService();
    private UserBLService userBL = BLServiceFactory.getUserBLService();
    private HotelBLService hotelBL = BLServiceFactory.getHotelBLService();

    public UserVO getUserInfo(String username) {
        try {
            return userBL.get(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modifyUserInfo(UserVO vo) {
        try {
            return userBL.modifyBasicInfo(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public MemberVO getMemberInfo(String username) {
        try {
            return customerBL.getMemberInfo(username);
        } catch (Exception e) {
            return null;
        }
    }

    public ResultMessage enroll(MemberVO vo) {
        try {
            return customerBL.enroll(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ArrayList<CreditRecordVO> getCreditRecord(String username) {
        try {
            return creditBL.get(username);
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

    public ArrayList<HotelSearchVO> searchHotel(int areaId, String username) {
        try {
            return hotelBL.search(areaId, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
