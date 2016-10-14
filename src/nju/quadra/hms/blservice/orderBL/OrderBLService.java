package nju.quadra.hms.blservice.orderBL;

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
    public PriceVO getPrice(OrderVO vo);

    public ResultMessage add(OrderVO vo);

    public ArrayList<OrderVO> getByCustomer(String username);

    public ArrayList<OrderVO> getByHotel(int hotelId);

    public ArrayList<OrderVO> getByState(OrderState state);

    public ResultMessage undoExcepted(OrderVO vo, boolean returnAllCredit);

    public ResultMessage undoUnfinished(OrderVO vo);

    public ResultMessage addRank(OrderRankVO vo);
}
