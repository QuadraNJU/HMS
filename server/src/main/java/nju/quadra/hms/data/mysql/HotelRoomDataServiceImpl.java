package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.HotelRoomPO;
import nju.quadra.hms.po.UserPO;

public class HotelRoomDataServiceImpl implements HotelRoomDataService {

	@Override
	public ArrayList<HotelRoomPO> get(int hotelId) throws Exception {
		ArrayList<HotelRoomPO> result = new ArrayList<>();
		PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("SELECT * FROM `hotelroom` WHERE `hotelid` = ?");
        pst.setInt(1, hotelId);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
        	HotelRoomPO po = new HotelRoomPO(
        			rs.getInt("id"), 
        			rs.getInt("hotelId"), 
        			rs.getString("name"), 
        			rs.getInt("total"), 
        			rs.getDouble("price")
        	);
        	result.add(po);
        }

		if(!rs.isClosed()) rs.close();
		if(!pst.isClosed()) pst.close();
		MySQLManager.closeConnection();
        return result;
	}

	@Override
	public void insert(HotelRoomPO po) throws Exception {
		PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("INSERT INTO `hotelroom` (`id`, `hotelid`, `name`, `total`, `price`) VALUES (?, ?, ?, ?, ?)");
		if (po.getId() > 0)
			pst.setInt(1, po.getId());
		else
			pst.setNull(1, Types.INTEGER);
        pst.setInt(2, po.getHotelId());
        pst.setString(3, po.getName());
        pst.setInt(4, po.getTotal());
        pst.setDouble(5, po.getPrice());

        pst.executeUpdate();
        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();

	}

	@Override
	public void delete(HotelRoomPO po) throws Exception {
		PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `hotelroom` WHERE `id` = ?");
        pst.setInt(1, po.getId());
        int result = pst.executeUpdate();
        if (result == 0) {
            throw new Exception("HotelRoom not found");
        }
        if(!pst.isClosed()) pst.close();
        MySQLManager.closeConnection();
	}

	@Override
	public void update(HotelRoomPO po) throws Exception {
		delete(po);
		insert(po);
	}

}