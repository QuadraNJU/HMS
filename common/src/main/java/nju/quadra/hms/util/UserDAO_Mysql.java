package nju.quadra.hms.util;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by RaUkonn on 2016/11/7.
 */
public class UserDAO_Mysql implements UserDAO {
    private static SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
    private static TemplateDAO_Mysql template = new TemplateDAO_Mysql();

    public ResultMessage insert(UserPO po) {

        String sql = "insert into user(username, password, name, contact, type, membertype, birthday, companyname) " +
                "values('" + po.getUsername() + "', '" + po.getPassword() + "', '" + po.getName() + "', '" + po.getContact() + "', '" + po.getType().ordinal() + "', '" + po.getMemberType().ordinal() + "', '" + dateformat.format(po.getBirthday()) + "', '" + "company111" + "')";

        return template.operate(sql);
    }

    @Override
    public ResultMessage delete(UserPO po) {
        String sql = "delete from user where username = '" + po.getUsername() + "'";
        return template.operate(sql);
    }

    @Override
    public ResultMessage update(UserPO po) {

        String sql = "UPDATE user SET password = '" + po.getPassword() + "', name = '" + po.getName() + "', contact = '" + po.getContact() + "', type = '" + po.getType().ordinal() + "', membertype = '" + po.getMemberType().ordinal() + "', birthday = '" + dateformat.format(po.getBirthday()) + "', companyname = '" + po.getCompanyName() + "' " +
                "WHERE username = '" + po.getUsername() + "'";
        return template.operate(sql);
    }

    @Override
    public ArrayList<UserPO> getAll() {
        String sql = "select * from user";
        ResultSet rs = template.query(sql);
        ArrayList<UserPO> arr = convertToArray(rs);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    @Override
    public ArrayList<UserPO> get(String username) {
        String sql = "select * from user where username = '" + username + "'";
        ResultSet rs = template.query(sql);
        ArrayList<UserPO> arr = convertToArray(rs);
        try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return arr;
    }

    public void cleanAll() {
        String sql = "delete from user";
        template.operate(sql);
    }

    private ArrayList<UserPO> convertToArray(ResultSet rs) {
        ArrayList<UserPO> result = new ArrayList<>();
        try {
            while(rs.next()) {
                String theUser = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                UserType type = UserType.getById(rs.getInt("type"));
                MemberType memberType = MemberType.getById(rs.getInt("membertype"));
                Date birthday = rs.getDate("birthday");
                String companyname = rs.getString("companyname");
                UserPO po = new UserPO(theUser, password, name, contact, type, memberType, birthday, companyname);
                result.add(po);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}