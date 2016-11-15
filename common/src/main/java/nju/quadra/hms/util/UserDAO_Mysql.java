package nju.quadra.hms.util;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.UserPO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

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

    public ResultMessage update(UserPO po) {

        String sql = "UPDATE user SET password = '" + po.getPassword() + "', name = '" + po.getName() + "', contact = '" + po.getContact() + "', type = '" + po.getType().ordinal() + "', membertype = '" + po.getMemberType().ordinal() + "', birthday = '" + dateformat.format(po.getBirthday()) + "', companyname = '" + po.getCompanyName() + "' " +
                "WHERE username = '" + po.getUsername() + "'";
        return template.operate(sql);
    }

    public ArrayList<UserPO> getAll() {

        String sql = "select * from user";
        ArrayList<Object> queryResult = template.query(sql);
        ArrayList<UserPO> result = new ArrayList<>();
        result.stream().filter(o -> o instanceof UserPO).forEach(o -> {
            UserPO po = (UserPO)o;
            result.add(po);
        });
        return result;
    }

    @Override
    public ArrayList<UserPO> get(String username) {
        String sql = "select * from user where username = '" + username + "'";
        ArrayList<Object> queryResult = template.query(sql);
        ArrayList<UserPO> result = new ArrayList<>();
        result.stream().filter(o -> o instanceof UserPO).forEach(o -> {
            UserPO po = (UserPO)o;
            result.add(po);
        });
        return result;
    }

    public void cleanAll() {
        String sql = "delete from user";
        template.operate(sql);
    }
}