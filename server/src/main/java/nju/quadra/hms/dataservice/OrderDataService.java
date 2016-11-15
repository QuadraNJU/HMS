package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface OrderDataService {
    ArrayList<OrderPO> getByCustomer(String username);

    ArrayList<OrderPO> getByHotel(int hotelId);

    ArrayList<OrderPO> getByState(OrderState state);

    void insert(OrderPO po);

    void update(OrderPO po);
}
