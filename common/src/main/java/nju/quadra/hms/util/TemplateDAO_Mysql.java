package nju.quadra.hms.util;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Observable;

/**
 * Created by admin on 2016/11/14.
 */
public class TemplateDAO_Mysql {
    private static Connection conn = null;
    private static Statement st = null;
    private static String adminUsername = "admin";
    private static String adminPassword = "admin";
    private static String ipaddress = "localhost";

    public ResultMessage operate(String sql) {
        Boolean isSucc = true;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://"+ ipaddress + "/hmsdatabase";
            conn = DriverManager.getConnection(url, adminUsername, adminPassword);
            st = conn.createStatement();
//            String sql = "insert into user(username, password, name, contact, type, membertype, birthday, companyname) " +
//                    "values('" + po.getUsername() + "', '" + po.getPassword() + "', '" + po.getName() + "', '"+ po.getContact() + "', '" + po.getType().ordinal() + "', '" + po.getMemberType().ordinal() + "', '" + dateformat.format(po.getBirthday()) + "', '" + "company111" + "')";
            isSucc = st.execute(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_ERROR, "环境未配置好MySQL数据库，请检查(ClassNotFoundExecption)。");
        } catch (SQLException e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_ERROR, "SQL语句错误，请检查(ClassNotFoundExecption)。");

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally{
            //释放资源
            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResultMessage(ResultMessage.RESULT_ERROR, "Statement关闭异常，请检查(SQLExecption)。");
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResultMessage(ResultMessage.RESULT_ERROR, "Connection关闭异常，请检查(SQLExecption)。");
                }
            }
        }
        if(isSucc) {
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);
        } else {
            return new ResultMessage(ResultMessage.RESULT_ERROR, "数据库更新失败，请重试。");
        }
    }

    public ResultSet query(String sql) {
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://" + ipaddress + "/hmsdatabase";
            conn = DriverManager.getConnection(url, adminUsername, adminPassword);
            st = conn.createStatement();
            rs = st.executeQuery(sql);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return rs;
    }
}
