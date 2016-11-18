package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nju.quadra.hms.dataservice.HotelPromotionDataService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.po.HotelPromotionPO;


public class HotelPromotionDataServiceImpl implements HotelPromotionDataService{

	@Override
	public ArrayList<HotelPromotionPO> get(int hotelId) throws Exception{
		 ArrayList<HotelPromotionPO> result = new ArrayList<>();
		 PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("SELECT * FROM `hotelpromotion`");
		 ResultSet rs = pst.executeQuery();
		 while(rs.next()){
			 HotelPromotionPO po = new HotelPromotionPO(
					 rs.getInt("id"),
					 rs.getInt("hotelid"),
					 rs.getString("name"),
					 HotelPromotionType.getById(rs.getInt("type")),
					 rs.getDate("starttime"),
					 rs.getDate("endtime"),
					 rs.getDouble("promotion"),
					 rs.getString("cooperation")
					 );
			 result.add(po);
	        }
		 return result;
	    }

	@Override
	public void insert(HotelPromotionPO po) throws Exception {
		  PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("INSERT INTO `hotelpromotion` (`id`, `hotelid, `name`, `type`, `starttime`, `endtime`, `promotion`, `cooperation`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		  pst.setInt(1,po.getId());
		  pst.setInt(2,po.getHotelId());
		  pst.setString(3,po.getName());
		  pst.setInt(4, po.getType().ordinal());
		  pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(po.getStartTime()));
		  pst.setString(6, new SimpleDateFormat("yyyy-MM-dd").format(po.getEndTime()));
		  pst.setDouble(7,po.getPromotion());
		  pst.setString(8,po.getCooperation());
		  pst.executeUpdate();
	}

	@Override
	public void delete(HotelPromotionPO po) throws Exception {
	     PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("DELETE FROM `hotelpromotion` WHERE `hotelid` = ?");
	     pst.setInt(1, po.getHotelId());
	     int result = pst.executeUpdate();
	     if(result == 0) {
	    	 throw new Exception("HotelPromotion not found");
	     }
	}

	@Override
	public void update(HotelPromotionPO po) throws Exception {
		// Delete first
		delete(po);
		// Then insert it again
		insert(po);
	}

}
