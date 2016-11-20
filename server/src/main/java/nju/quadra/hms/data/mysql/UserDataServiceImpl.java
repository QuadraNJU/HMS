package nju.quadra.hms.data.mysql;

import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/11/15.
 */
public class UserDataServiceImpl implements UserDataService {

    @Override
    public ArrayList<UserPO> getAll() throws Exception {
        ArrayList<UserPO> result = new ArrayList<>();
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `user`");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            UserPO po = new UserPO(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    UserType.getById(rs.getInt("type")),
                    MemberType.getById(rs.getInt("membertype")),
                    rs.getDate("birthday"),
                    rs.getString("companyname")
            );
            result.add(po);
        }
        return result;
    }

    @Override
    public UserPO get(String username) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `user` WHERE `username` = ?");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            UserPO po = new UserPO(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    UserType.getById(rs.getInt("type")),
                    MemberType.getById(rs.getInt("membertype")),
                    rs.getDate("birthday"),
                    rs.getString("companyname")
            );
            return po;
        } else {
            return null;
        }
    }

    @Override
    public void insert(UserPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `user` (`username`, `password`, `name`, `contact`, `type`, `membertype`, `birthday`, `companyname`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        pst.setString(1, po.getUsername());
        pst.setString(2, po.getPassword());
        pst.setString(3, po.getName());
        pst.setString(4, po.getContact());
        pst.setInt(5, po.getType().ordinal());
        pst.setInt(6, po.getMemberType().ordinal());
        pst.setString(7, new SimpleDateFormat("yyyy-MM-dd").format(po.getBirthday().getTime()));
        pst.setString(8, po.getCompanyName());
        pst.executeUpdate();
    }

    @Override
    public void delete(UserPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `user` WHERE `username` = ?");
        pst.setString(1, po.getUsername());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("User not found");
        }
    }

    @Override
    public void update(UserPO po) throws Exception {
        // Delete first
        delete(po);
        // Then insert it again
        insert(po);
    }

}
