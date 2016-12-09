package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.OrderBLService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.*;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/23.
 */
public class OrderController {

    private OrderBLService orderBL = BLServiceFactory.getOrderBLService();

    public PriceVO getPrice(OrderVO vo) {
        try {
            return orderBL.getPrice(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(OrderVO vo) {
        try {
            return orderBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderVO> getByState(OrderState state) {
        try {
            return orderBL.getByState(state);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage undoDelayed(OrderVO vo, boolean returnAllCredit) {
        try {
            return orderBL.undoDelayed(vo, returnAllCredit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addRank(OrderRankVO vo) {
        try {
            return orderBL.addRank(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
