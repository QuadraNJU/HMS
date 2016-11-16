package nju.quadra.hms.data.mysql;

import nju.quadra.hms.po.OrderPO;

import java.sql.PreparedStatement;

/**
 * Created by RaUkonn on 2016/11/16.
 */
public class OrderDataServiceGarbageCollector {
    public void delete(OrderPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `orders` WHERE `id` = ?");
        pst.setInt(1, po.getId());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("Order not found");
        }
    }

    public void clean() throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `orders`");
        pst.executeUpdate();
    }
}