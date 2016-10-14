package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderDataService_Stub implements OrderDataService {
    private ArrayList<OrderPO> list;
    private OrderPO po;

    public OrderDataService_Stub() {
        list = new ArrayList<>();
        po = new OrderPO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                new ArrayList<>(), false, 99.9, 5, "是个休闲的好去处");
        list.add(po);
    }

    @Override
    public ArrayList<OrderPO> selectByCustomer(String username) {
        return list;
    }

    @Override
    public ArrayList<OrderPO> selectByHotel(int hotelId) {
        return list;
    }

    @Override
    public void insert(OrderPO po) {
        System.out.println("Insert OrderPO success");
    }

    @Override
    public void update(OrderPO po) {
        System.out.println("Update OrderPO success");
    }
}
