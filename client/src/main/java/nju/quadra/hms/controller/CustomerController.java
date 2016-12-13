package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.*;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.*;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/11/25.
 */
public class CustomerController {

    private final CustomerBLService customerBL = BLServiceFactory.getCustomerBLService();
    private final CreditRecordBLService creditBL = BLServiceFactory.getCreditRecordBLService();
    private final UserBLService userBL = BLServiceFactory.getUserBLService();
    private final HotelBLService hotelBL = BLServiceFactory.getHotelBLService();
    private final OrderBLService orderBL = BLServiceFactory.getOrderBLService();

    public UserVO getUserInfo(String username) {
        try {
            return userBL.get(username);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public ResultMessage modifyUserInfo(UserVO vo) {
        try {
            return userBL.modifyBasicInfo(vo);
        } catch (Exception e) {
            // e.printStackTrace();
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
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ArrayList<CreditRecordVO> getCreditRecord() {
        try {
            return creditBL.get("");
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public ArrayList<AreaVO> getAllArea() {
        try {
            return hotelBL.getAllArea();
        } catch (Exception e) {
            // e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public ArrayList<HotelSearchVO> searchHotel(int areaId, String username) {
        try {
            return hotelBL.search(areaId, username);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public PriceVO getOrderPrice(OrderVO vo) {
        try {
            return orderBL.getPrice(vo);
        } catch (Exception e) {
            // e.printStackTrace();
            return new PriceVO("发生网络通信错误，请稍后重试");
        }
    }

    public ResultMessage addOrder(OrderVO vo) {
        try {
            return orderBL.add(vo);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ArrayList<OrderDetailVO> getOrders(String username) {
        try {
            return orderBL.getByCustomer(username);
        } catch (Exception e) {
            // e.printStackTrace();
            return null;
        }
    }

    public ResultMessage undoUnfinishedOrder(int orderId) {
        try {
            return orderBL.undoUnfinished(orderId);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

    public ResultMessage rankOrder(OrderRankVO vo) {
        try {
            return orderBL.addRank(vo);
        } catch (Exception e) {
            // e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
