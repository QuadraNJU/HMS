package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.AreaPO;
import nju.quadra.hms.po.HotelPO;
import nju.quadra.hms.po.UserPO;

public class HotelDataServiceImpl implements HotelDataService {

    @Override
    public ArrayList<HotelPO> getAll() throws Exception {
        ArrayList<HotelPO> result = new ArrayList<>();
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `hotel`");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            HotelPO po = createPO(rs);
            result.add(po);
        }
        return result;
    }

    @Override
    public HotelPO getById(int id) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `hotel` WHERE `id` = ?");
        pst.setInt(1, id);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return createPO(rs);
        }
        return null;
    }

    @Override
    public ArrayList<HotelPO> getByArea(int areaId) throws Exception {
        ArrayList<HotelPO> result = new ArrayList<>();
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `hotel` WHERE `areaid` = ?");
        pst.setInt(1, areaId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            HotelPO po = createPO(rs);
            result.add(po);
        }
        return result;
    }

    @Override
    public HotelPO getByStaff(String staff) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `hotel` WHERE `staff` = ?");
        pst.setString(1, staff);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            return createPO(rs);
        }
        return null;
    }

    @Override
    public ArrayList<AreaPO> getAllArea() throws Exception {
        ArrayList<AreaPO> result = new ArrayList<>();
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `area` ORDER BY `cityname`");
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            AreaPO po = new AreaPO(rs.getInt("id"), rs.getString("cityname"), rs.getString("name"));
            result.add(po);
        }
        return result;
    }

    @Override
    public void insert(HotelPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `hotel` (`id`, `name`, `areaId`, `address`, `description`, `facilities`, `star`, `staff`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
        if (po.getId() > 0)
            pst.setInt(1, po.getId());
        else
            pst.setNull(1, Types.INTEGER);
        pst.setString(2, po.getName());
        pst.setInt(3, po.getAreaId());
        pst.setString(4, po.getAddress());
        pst.setString(5, po.getDescription());
        pst.setString(6, po.getFacilities());
        pst.setString(7, po.getStar());
        pst.setString(8, po.getStaff());

        pst.executeUpdate();
    }

    @Override
    public void delete(HotelPO po) throws Exception {
        PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `hotel` WHERE `id` = ?");
        pst.setInt(1, po.getId());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("Hotel not found");
        }
    }

    @Override
    public void update(HotelPO po) throws Exception {
        delete(po);
        insert(po);
    }

    private HotelPO createPO(ResultSet rs) throws Exception {
        return new HotelPO(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getInt("areaId"),
                rs.getString("address"),
                rs.getString("description"),
                rs.getString("facilities"),
                rs.getString("star"),
                rs.getString("staff")
        );
    }

}
