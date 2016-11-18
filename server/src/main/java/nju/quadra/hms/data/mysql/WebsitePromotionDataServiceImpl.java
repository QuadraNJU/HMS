package nju.quadra.hms.data.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.po.WebsitePromotionPO;

public class WebsitePromotionDataServiceImpl implements WebsitePromotionDataService{

	@Override
	public ArrayList<WebsitePromotionPO> getAll() throws Exception{
		 ArrayList<WebsitePromotionPO> result = new ArrayList<>();
		 PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("SELECT * FROM `websitepromotion`");
		 ResultSet rs = pst.executeQuery();
		 while(rs.next()){
			 WebsitePromotionPO po = new WebsitePromotionPO(
					 rs.getInt("id"),
					 rs.getString("name"),
					 WebsitePromotionType.getById(rs.getInt("type")),
					 rs.getDate("starttime"),
					 rs.getDate("endtime"),
					 rs.getDouble("promotion"),
					 rs.getInt("areaid"),
					 rs.getString("memberlevel")
					 );
			 result.add(po);
	        }
		 return result;
	    }

	@Override
	public void insert(WebsitePromotionPO po) throws Exception{
		 PreparedStatement pst = MySQLManager.getConnection()
	                .prepareStatement("INSERT INTO `websitepromotion` (`id`, `name`, `type`, `starttime`, `endtime`, "
	                		+ "`promotion`, `areaid` , `memberlevel`) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		  if (po.getId() > 0)
	         pst.setInt(1, po.getId());
	      else
	         pst.setNull(1, Types.INTEGER);
		  pst.setString(2,po.getName());
		  pst.setInt(3, po.getType().ordinal());
		  pst.setString(4, new SimpleDateFormat("yyyy-MM-dd").format(po.getStartTime()));
		  pst.setString(5, new SimpleDateFormat("yyyy-MM-dd").format(po.getEndTime()));
		  pst.setDouble(6,po.getPromotion());
		  pst.setInt(7,po.getAreaId());
		  pst.setString(8,po.getMemberLevel());
		  pst.executeUpdate();
	}

	@Override
	public void delete(WebsitePromotionPO po) throws Exception{
		  PreparedStatement pst = MySQLManager.getConnection()
                .prepareStatement("DELETE FROM `websitepromotion` WHERE `id` = ?");
		  pst.setDouble(1, po.getId());
		  int result = pst.executeUpdate();
		  if(result == 0) {
			  throw new Exception("WebsitePromotion not found");
		  }
	}

	@Override
	public void update(WebsitePromotionPO po) throws Exception{
		// Delete first
		delete(po);
		// Then insert it again
		insert(po);
	}

}
