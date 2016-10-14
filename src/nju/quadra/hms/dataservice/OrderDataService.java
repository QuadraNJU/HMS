package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface OrderDataService {
    ArrayList<OrderPO> selectByCustomer(String username);

    ArrayList<OrderPO> selectByHotel(int hotelId);

    void insert(OrderPO po);

    void update(OrderPO po);
}
