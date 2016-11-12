package nju.quadra.hms.util;

import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.sql.*;
import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/7.
 */
public class UserDataService {
    private static Connection conn = null;
    private static Statement st = null;
    private static String adminUsername = "admin";
    private static String adminPassword = "admin";

    public static ResultMessage insert(UserPO po) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/hmsdatabase";
            conn = DriverManager.getConnection(url, adminUsername, adminPassword);
            st = conn.createStatement();
            String sql = "insert into user(username, password, name, contact, type, membertype, birthday, companyname) " +
                    "values('" + po.getUsername() + "', '" + po.getPassword() + "', '" + po.getName() + "', '"+ po.getContact() + "', '" + po.getType().ordinal() + "', '" + po.getMemberType().ordinal() + "', '" + "1996-11-21" + "', '" + "company111" + "')";
            st.executeUpdate(sql);

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
            return new ResultMessage(ResultMessage.RESULT_SUCCESS);

    }

    public static ResultMessage update(String username, UserPO po) {
        Boolean isSucc = true;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/hmsdatabase";
            conn = DriverManager.getConnection(url, adminUsername, adminPassword);
            st = conn.createStatement();
            String sql = "UPDATE user SET username = '" + po.getUsername() + "', password = '" + po.getPassword() + "', name = '" + po.getName() + "', contact = '" + po.getContact() + "', type = '" + po.getType() + "', membertype = '" + po.getMemberType() + "', birthday = '" + po.getBirthday() + "', companyname = '" + po.getCompanyName() + "'" +
                    "WHERE username = '" + username + "'";
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
            return new ResultMessage(ResultMessage.RESULT_ERROR, "更新失败，请重试。");
        }

    }

    public static ArrayList<UserPO> selectAll() {
        ArrayList<UserPO> result = new ArrayList<UserPO>();
        ResultSet rs = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/hmsdatabase";
            conn = DriverManager.getConnection(url, adminUsername, adminPassword);
            st = conn.createStatement();
            String sql = "select * from user";
            rs = st.executeQuery(sql);
            while(rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String contact = rs.getString("contact");
                UserType type = UserType.getById(rs.getInt("type"));
                MemberType memberType = MemberType.getById(rs.getInt("membertype"));
                Date birthday = rs.getDate("birthday");
                String companyname = rs.getString("companyname");
                UserPO po = new UserPO(username, password, name, contact, type, memberType, birthday, companyname);
                result.add(po);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally{
            //释放资源
            if(rs != null){
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if(st != null){
                try {
                    st.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }
}