package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.OrderDataService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.po.OrderPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderDataService_Driver {
    public void drive(OrderDataService orderDataService) {
        try {
            ArrayList<OrderPO> list = orderDataService.selectByHotel(1);
            orderDataService.selectByCustomer("quadra2");
            orderDataService.selectByState(OrderState.UNCOMPLETED);
            orderDataService.insert(new OrderPO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                    new ArrayList<>(), false, 99.9, OrderState.RANKED, 5, "是个休闲的好去处"));
            orderDataService.update(list.get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
