package nju.quadra.hms.data.mysql;

import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.CreditRecordPO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/16.
 */
public class CreditDataServiceImpl implements CreditDataService {
    @Override
    public ArrayList<CreditRecordPO> get(String username) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `creditrecord` WHERE username = ? ORDER BY `timestamp` DESC");
        pst.setString(1, username);
        ResultSet rs = pst.executeQuery();
        ArrayList<CreditRecordPO> result = convertToArrayList(rs);
        return result;
    }

    @Override
    public void insert(CreditRecordPO po) throws Exception{
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `creditrecord` (`username`, `orderid`, `action`, `diff`) VALUES (?, ?, ?, ?)");
        pst.setString(1, po.getUsername());
        pst.setInt(2, po.getOrderId());
        pst.setInt(3, po.getAction().ordinal());
        pst.setDouble(4, po.getDiff());
        pst.executeUpdate();
    }

    @Override
    public void delete(CreditRecordPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `creditrecord` WHERE `id` = ?");
        pst.setInt(1, po.getId());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("Credit record not found");
        }
    }

    private ArrayList<CreditRecordPO> convertToArrayList(ResultSet rs) throws Exception{
        ArrayList<CreditRecordPO> result = new ArrayList<>();
        while (rs.next()) {
            CreditRecordPO po = new CreditRecordPO(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getTimestamp("timestamp"),
                    rs.getInt("orderid"),
                    CreditAction.getById(rs.getInt("action")),
                    rs.getDouble("diff")
            );
            result.add(po);
        }
        return result;
    }
}
