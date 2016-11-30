package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.*;

import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/23.
 */
public class OrderController {

    private HttpRemote orderRemote;

    public OrderController() {
        orderRemote = new HttpRemote("OrderBL");
    }

    public PriceVO getPrice(OrderVO vo) {
        try {
            return orderRemote.invoke(PriceVO.class, "getPrice", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(OrderVO vo) {
        try {
            return orderRemote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderVO> getByCustomer(String username) {
        try {
            return orderRemote.invoke(new TypeToken<ArrayList<OrderVO>>(){}.getType(), "getByCustomer", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderVO> getByHotel(int hotelId) {
        try {
            return orderRemote.invoke(new TypeToken<ArrayList<OrderVO>>(){}.getType(), "getByHotel", hotelId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<OrderVO> getByState(OrderState state) {
        try {
            return orderRemote.invoke(new TypeToken<ArrayList<OrderVO>>(){}.getType(), "getByState", state);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage undoDelayed(OrderVO vo, boolean returnAllCredit) {
        try {
            return orderRemote.invoke(ResultMessage.class, "undoDelayed", vo, returnAllCredit);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage undoUnfinished(OrderVO vo) {
        try {
            return orderRemote.invoke(ResultMessage.class, "undoUnfinished", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage finish(OrderVO vo) {
        try {
            return orderRemote.invoke(ResultMessage.class, "finish", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage addRank(OrderRankVO vo) {
        try {
            return orderRemote.invoke(ResultMessage.class, "addRank", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
