package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.orderBL.OrderBLService;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.vo.OrderRankVO;
import nju.quadra.hms.vo.OrderVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class OrderBLService_Driver {
    public void drive(OrderBLService orderBLService) {
        try {
            ArrayList<OrderVO> list = orderBLService.getByCustomer("quadra2");
            orderBLService.getByHotel(1);
            orderBLService.getByState(OrderState.DELAYED);
            OrderVO vo = new OrderVO(1, "quadra2", 1, new Date(2016, 11, 11), new Date(2016, 11, 12), 1, 1, 2,
                    new ArrayList<>(), false, 99.9, OrderState.UNCOMPLETED, -1, null);
            orderBLService.getPrice(vo);
            orderBLService.add(vo);
            orderBLService.undoUnfinished(vo);
            orderBLService.undoDelayed(vo, false);
            orderBLService.finish(vo);
            orderBLService.addRank(new OrderRankVO(vo.id, 5, "来去方便"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
