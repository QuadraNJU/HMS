package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;

import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
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
        	HotelPO po = new HotelPO(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("cityId"),
                    rs.getInt("areaId"),
                    rs.getString("address"),
                    rs.getString("description"),
                    rs.getString("facilities"),
                    rs.getString("staff")
            );
            result.add(po);
        }
        return result;
	}

	@Override
	public HotelPO getById(int id) throws Exception {
		HotelPO po = null;
		PreparedStatement pst = MySQLManager.getConnection()
				.prepareStatement("SELECT * FROM `hotel` WHERE `id` = ?");
		pst.setInt(1, id);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			po = new HotelPO(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getInt("cityId"),
					rs.getInt("areaId"),
					rs.getString("address"),
					rs.getString("description"),
					rs.getString("facilities"),
					rs.getString("staff")
			);
		}
		return po;

	}

	@Override
	public ArrayList<HotelPO> getByArea(int areaId) throws Exception {
		ArrayList<HotelPO> result = new ArrayList<>();
		PreparedStatement pst = MySQLManager.getConnection()
				.prepareStatement("SELECT * FROM `hotel` WHERE `areaid` = ?");
		pst.setInt(1, areaId);
		ResultSet rs = pst.executeQuery();
		while (rs.next()) {
			HotelPO po = new HotelPO(
					rs.getInt("id"),
					rs.getString("name"),
					rs.getInt("cityId"),
					rs.getInt("areaId"),
					rs.getString("address"),
					rs.getString("description"),
					rs.getString("facilities"),
					rs.getString("staff")
			);
			result.add(po);
		}
		return result;
	}

	@Override
	public void insert(HotelPO po) throws Exception {
		PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `hotel` (`id`, `name`, `cityId`, `areaId`, `address`,`description`, `facilities`, `staff`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		if (po.getId() > 0)
			pst.setInt(1, po.getId());
		else
			pst.setNull(1, Types.INTEGER);
        pst.setString(2, po.getName());
        pst.setInt(3, po.getCityId());
        pst.setInt(4, po.getAreaId());
        pst.setString(5, po.getAddress());
        pst.setString(6, po.getDescription());
        pst.setString(7, po.getFacilities());
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
	
}
