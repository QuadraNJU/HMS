package nju.quadra.hms.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import nju.quadra.hms.data.mysql.HotelPromotionDataServiceImpl;
import nju.quadra.hms.data.mysql.WebsitePromotionDataServiceImpl;
import nju.quadra.hms.dataservice.WebsitePromotionDataService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.WebsitePromotionType;
import nju.quadra.hms.po.HotelPromotionPO;
import nju.quadra.hms.po.WebsitePromotionPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class WebsitePromotionServiceTest {
	private WebsitePromotionDataService websitePromotionDataService;
	
	 @Before
	 public void init() {
		 websitePromotionDataService = new WebsitePromotionDataServiceImpl();
	 }
	 
	 @Test
	 public void test1_Insert() {
		 WebsitePromotionPO po = new WebsitePromotionPO(1, "TEST|username", WebsitePromotionType.TIME_PROMOTION, new Date(1996-1900, 11-1, 21), 
	        		new Date(1996-1900, 11-1, 25), 0, 1, null);
	        try {
	        	websitePromotionDataService.insert(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test2_Get() {
	        try {
	            ArrayList<WebsitePromotionPO> list = websitePromotionDataService.getAll();
	            assertEquals(list.get(0).getName(), "TEST|username");
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test3_Update() {
	    	 WebsitePromotionPO po = new WebsitePromotionPO(1, "TEST|usernameUpdate", WebsitePromotionType.TIME_PROMOTION, new Date(1996-1900, 11-1, 21), 
		        		new Date(1996-1900, 11-1, 25), 0, 1, null);
	        try {
	        	websitePromotionDataService.update(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test4_Insert() {
	    	WebsitePromotionPO po = new WebsitePromotionPO(3, "TEST|usernameNew", WebsitePromotionType.TIME_PROMOTION, new Date(1996-1900, 11-1, 21), 
	        		new Date(1996-1900, 11-1, 25), 0, 1, null);
	        try {
	        	websitePromotionDataService.insert(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }
	    
	    @Test
	    public void test5_Get() {
	    	try {
	            ArrayList<WebsitePromotionPO> list = websitePromotionDataService.getAll();
	            assertEquals(list.get(0).getName(), "TEST|usernameUpdate");
	            assertEquals(list.get(1).getName(), "TEST|usernameNew");
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test6_Delete() {
	        try {
	        	ArrayList<WebsitePromotionPO> list = websitePromotionDataService.getAll();
	            for(WebsitePromotionPO po: list)
	            	websitePromotionDataService.delete(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }
	 
}
