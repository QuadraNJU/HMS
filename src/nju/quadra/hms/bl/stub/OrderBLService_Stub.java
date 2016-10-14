package nju.quadra.hms.bl.stub;

import nju.quadra.hms.blservice.orderBL.OrderBLService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;
import nju.quadra.hms.vo.PriceVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderBLService_Stub implements OrderBLService {
    private ArrayList<OrderVO> list;
    private OrderVO vo;

    public OrderBLService_Stub() {
        list = new ArrayList<>();
        vo = new OrderVO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处");
        list.add(vo);
    }

    @Override
    public PriceVO getPrice(OrderVO vo) {
        return new PriceVO(129.9, 129.9, null, null);
    }

    @Override
    public ResultMessage add(OrderVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ArrayList<OrderVO> getByCustomer(String username) {
        return list;
    }

    @Override
    public ArrayList<OrderVO> getByHotel(int hotelId) {
        return list;
    }

    @Override
    public ArrayList<OrderVO> getByState(OrderState state) {
        return list;
    }

    @Override
    public ResultMessage undoExcepted(OrderVO vo, boolean returnAllCredit) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage undoUnfinished(OrderVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }

    @Override
    public ResultMessage addRank(OrderRankVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
