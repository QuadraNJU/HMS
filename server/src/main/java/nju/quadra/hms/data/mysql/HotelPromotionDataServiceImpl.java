package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
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
	                .prepareStatement("SELECT * FROM `hotelpromotion` WHERE `hotelid` = ?");
		 pst.setInt(1, hotelId);
		 ResultSet rs = pst.executeQuery();
		 while(rs.next()){
			 HotelPromotionPO po = convertToSingle(rs);
			 result.add(po);
	        }
		 return result;
	}

	@Override
	public HotelPromotionPO getById(int promotionId) throws Exception{
		PreparedStatement pst = MySQLManager.getConnection()
				.prepareStatement("SELECT * FROM `hotelpromotion` WHERE `id` = ?");
		pst.setInt(1, promotionId);
		ResultSet rs = pst.executeQuery();
		rs.next();
		return convertToSingle(rs);
	}

	@Override
	public void insert(HotelPromotionPO po) throws Exception {
		  PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("INSERT INTO `hotelpromotion` (`id`, `hotelid`, `name`, `type`, `starttime`, `endtime`, `promotion`, `cooperation`)"
	                		+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		  if (po.getId() > 0)
	            pst.setInt(1, po.getId());
	        else
	            pst.setNull(1, Types.INTEGER);
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
	                .prepareStatement("DELETE FROM `hotelpromotion` WHERE `id` = ?");
	     pst.setInt(1, po.getId());
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

	private HotelPromotionPO convertToSingle(ResultSet rs) throws Exception{
		return new HotelPromotionPO(
				rs.getInt("id"),
				rs.getInt("hotelid"),
				rs.getString("name"),
				HotelPromotionType.getById(rs.getInt("type")),
				rs.getDate("starttime"),
				rs.getDate("endtime"),
				rs.getDouble("promotion"),
				rs.getString("cooperation")
		);
	}
}
