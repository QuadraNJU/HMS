package nju.quadra.hms.dataservice;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface OrderDataService {
    ArrayList<OrderPO> getByCustomer(String username) throws Exception;

    ArrayList<OrderPO> getByHotel(int hotelId) throws Exception;

    ArrayList<OrderPO> getByState(OrderState state) throws Exception;

    OrderPO getById(int id) throws Exception;

    void insert(OrderPO po) throws Exception;

    void update(OrderPO po) throws Exception;

    void delete(OrderPO po) throws Exception;
}
