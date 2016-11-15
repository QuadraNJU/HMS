package nju.quadra.hms.util;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;

/**
 * Created by admin on 2016/11/14.
 */
public interface OrderDAO {
    ArrayList<OrderPO> selectByCustomer(String username);

    ArrayList<OrderPO> selectByHotel(int hotelId);

     ArrayList<OrderPO> selectByState(OrderState state);

     ResultMessage insert(OrderPO po);

     ResultMessage update(OrderPO po);
}
