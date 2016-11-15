package nju.quadra.hms.util;

import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.OrderPO;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by admin on 2016/11/14.
 */
public class OrderDAO_Mysql implements OrderDAO {
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    TemplateDAO_Mysql template = new TemplateDAO_Mysql();

    @Override
    public ArrayList<OrderPO> selectByCustomer(String username) {
        String sql = "select * from orders where username = '" + username + "'";
        ResultSet rs = template.query(sql);


        return new ArrayList<>();
    }

    @Override
    public ArrayList<OrderPO> selectByHotel(int hotelId) {
        String sql = "select * from orders where hotelid = '" + hotelId + "'";
        ArrayList<Object> queryResult = template.query(sql);
        ArrayList<OrderPO> result = new ArrayList<>();
        queryResult.stream().filter(o -> o instanceof OrderPO).forEach(o -> {
            OrderPO po = (OrderPO) o;
            result.add(po);
        });

        return result;
    }

    @Override
    public ArrayList<OrderPO> selectByState(OrderState state) {
        String sql = "select * from orders where state = '" + state.ordinal() + "'";
        ArrayList<Object> queryResult = template.query(sql);
        ArrayList<OrderPO> result = new ArrayList<>();
        queryResult.stream().filter(o -> o instanceof OrderPO).forEach(o -> {
            OrderPO po = (OrderPO) o;
            result.add(po);
        });

        return result;
    }

    @Override
    public ResultMessage insert(OrderPO po) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> persons = po.getPersons();
        for(int i = 0; i < persons.size(); i++) {
            sb.append(persons.get(i)).append('&');
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        String sql = "insert into orders(username, hotelid, state, startdate, enddate, roomid, roomcount, personcount, persons, haschildren, price, rank, comment) " +
                "values('" + po.getUsername() + "', '" + po.getHotelId() +"', '" + po.getState().ordinal() + "', '" + dateformat.format(po.getStartDate()) + "', '" + dateformat.format(po.getEndDate()) + "', '" + po.getRoomId() + "', '" + po.getRoomCount() + "', '" +
                po.getPersonCount() + "', '" + sb.toString() + "', '" + (po.isHasChildren()? 1: 0) + "', '" + po.getPrice() + "', '" + po.getRank() + "', '" + po.getComment() + "')";
        return template.operate(sql);
    }

    @Override
    public ResultMessage update(OrderPO po) {
        StringBuilder sb = new StringBuilder();
        ArrayList<String> persons = po.getPersons();
        for(int i = 0; i < persons.size(); i++) {
            sb.append(persons.get(i)).append('&');
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        String sql = "UPDATE orders SET hotelid = '" +
                po.getHotelId() + "', orderstate = '" + po.getState().ordinal() + "', startdate = '" + dateformat.format(po.getStartDate()) + "', enddate = '" + dateformat.format(po.getEndDate()) + "', roomid = '" + po.getRoomId() + "', roomcount = '" + po.getRoomCount() + "', personcount = '" +
                po.getPersonCount() + "', persons = '" + sb.toString() + "', haschildren = '" + po.isHasChildren() + "', price = '" + po.getPrice() + "', state = '" + po.getState() + "', rank = '" + po.getRank() + "', comment = '" + po.getComment() + "' " +
                "WHERE id = '" + po.getId() + "'";
        return template.operate(sql);
    }
}
