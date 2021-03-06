package nju.quadra.hms.data.mysql;

import nju.quadra.hms.dataservice.UserDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.UserPO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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
                    (rs.getDate("birthday") == null) ? null : rs.getDate("birthday").toLocalDate(),
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
            return new UserPO(
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("name"),
                    rs.getString("contact"),
                    UserType.getById(rs.getInt("type")),
                    MemberType.getById(rs.getInt("membertype")),
                    (rs.getDate("birthday") == null) ? null : rs.getDate("birthday").toLocalDate(),
                    rs.getString("companyname")
            );
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
        if (po.getBirthday() != null) {
            pst.setString(7, po.getBirthday().format(DateTimeFormatter.ofPattern("uuuu/MM/dd")));
        } else {
            pst.setNull(7, Types.DATE);
        }
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
