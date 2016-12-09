package nju.quadra.hms.blservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.OrderDetailVO;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;
import nju.quadra.hms.vo.PriceVO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface OrderBLService {
    PriceVO getPrice(OrderVO vo);

    ResultMessage add(OrderVO vo);

    ArrayList<OrderDetailVO> getByCustomer(String username);

    ArrayList<OrderDetailVO> getByHotel(int hotelId);

    ArrayList<OrderVO> getByState(OrderState state);

    ResultMessage undoDelayed(OrderVO vo, boolean returnAllCredit);

    ResultMessage undoUnfinished(int orderId);

    ResultMessage checkin(int orderId);

    ResultMessage checkout(int orderId);

    ResultMessage addRank(OrderRankVO vo);
}
