package nju.quadra.hms.util;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.OrderState;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.OrderPO;
import nju.quadra.hms.po.UserPO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        ArrayList<OrderPO> arr = convertToArray(rs);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public ArrayList<OrderPO> selectByHotel(int hotelId) {
        String sql = "select * from orders where hotelid = '" + hotelId + "'";
        ResultSet rs = template.query(sql);
        ArrayList<OrderPO> arr = convertToArray(rs);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public ArrayList<OrderPO> selectByState(OrderState state) {
        String sql = "select * from orders where state = '" + state.ordinal() + "'";
        ResultSet rs = template.query(sql);
        ArrayList<OrderPO> arr = convertToArray(rs);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public ResultMessage insert(OrderPO po) {
        ArrayList<String> persons = po.getPersons();
        String personsString = stringListToString(persons);
        String sql = "insert into orders(username, hotelid, state, startdate, enddate, roomid, roomcount, personcount, persons, haschildren, price, rank, comment) " +
                "values('" + po.getUsername() + "', '" + po.getHotelId() +"', '" + po.getState().ordinal() + "', '" + dateformat.format(po.getStartDate()) + "', '" + dateformat.format(po.getEndDate()) + "', '" + po.getRoomId() + "', '" + po.getRoomCount() + "', '" +
                po.getPersonCount() + "', '" + personsString + "', '" + (po.isHasChildren()? 1: 0) + "', '" + po.getPrice() + "', '" + po.getRank() + "', '" + po.getComment() + "')";
        return template.operate(sql);
    }

    @Override
    public ResultMessage update(OrderPO po) {
        ArrayList<String> persons = po.getPersons();
        String personsString = stringListToString(persons);
        String sql = "UPDATE orders SET hotelid = '" +
                po.getHotelId() + "', state = '" + po.getState().ordinal() + "', startdate = '" + dateformat.format(po.getStartDate()) +
                "', enddate = '" + dateformat.format(po.getEndDate()) + "', roomid = '" + po.getRoomId() + "', roomcount = '" + po.getRoomCount() +
                "', personcount = '" + po.getPersonCount() + "', persons = '" + personsString + "', haschildren = '" + po.isHasChildren() +
                "', price = '" + po.getPrice() + "', rank = '" + po.getRank() + "', comment = '" + po.getComment() + "' " +
                "WHERE id = '" + po.getId() + "'";
        return template.operate(sql);
    }

    private ArrayList<OrderPO> convertToArray(ResultSet rs) {
        ArrayList<OrderPO> result = new ArrayList<>();
        try {
            while(rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                int hotelid = rs.getInt("hotelid");
                OrderState state = OrderState.getById(rs.getInt("state"));
                Date startdate = rs.getDate("startdate");
                Date enddate = rs.getDate("enddate");
                int roomid = rs.getInt("roomid");
                int roomcount = rs.getInt("roomcount");
                int personcount = rs.getInt("personcount");
                String[] personsString = rs.getString("persons").split("&");
                ArrayList<String> persons = new ArrayList<>();
                for(String s: personsString) persons.add(s);
                boolean haschildren = rs.getBoolean("haschildren");
                double price = rs.getDouble("price");
                int rank = rs.getInt("rank");
                String comment = rs.getString("comment");
                OrderPO po = new OrderPO(id, username, hotelid, startdate, enddate, roomid, roomcount, personcount, persons, haschildren, price, state, rank, comment);
                result.add(po);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String stringListToString(List<String> persons) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < persons.size(); i++) {
            sb.append(persons.get(i)).append('&');
        }
        sb.deleteCharAt(sb.lastIndexOf("&"));
        return sb.toString();
    }
}
