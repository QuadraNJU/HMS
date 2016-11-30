package nju.quadra.hms.blservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
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

    ArrayList<OrderVO> getByCustomer(String username);

    ArrayList<OrderVO> getByHotel(int hotelId);

    ArrayList<OrderVO> getByState(OrderState state);

    ResultMessage undoDelayed(OrderVO vo, boolean returnAllCredit);

    ResultMessage undoUnfinished(OrderVO vo);

    ResultMessage finish(OrderVO vo);

    ResultMessage addRank(OrderRankVO vo);
}
