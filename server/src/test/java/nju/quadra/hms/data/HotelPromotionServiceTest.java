package nju.quadra.hms.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import nju.quadra.hms.data.mysql.HotelPromotionDataServiceImpl;
import nju.quadra.hms.dataservice.HotelPromotionDataService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.po.HotelPromotionPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelPromotionServiceTest {
	
	private HotelPromotionDataService hotelPromotionDataService;
	 @Before
	 public void init() {
	      hotelPromotionDataService = new HotelPromotionDataServiceImpl();
	 }
	 
	 @Test
	 public void test1_Insert() {
			HotelPromotionPO po = new HotelPromotionPO(1, 123456, "TEST|username", HotelPromotionType.TIME_PROMOTION, new Date(1996-1900, 11-1, 21), 
	        		new Date(1996-1900, 11-1, 25), 0, "TEST|cooperation");
	        try {
	           hotelPromotionDataService.insert(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test2_Get() {
	        try {
	            ArrayList<HotelPromotionPO> list = hotelPromotionDataService.get(123456);
	            assertEquals(list.get(0).getName(), "TEST|username");
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test3_Update() {
	    	HotelPromotionPO po = new HotelPromotionPO(1, 123456, "TEST|usernameUpdate", HotelPromotionType.TIME_PROMOTION, new Date(1996-1900, 11-1, 21), 
	        		new Date(1996-1900, 11-1, 25), 0, "TEST|cooperation");
	        try {
	            hotelPromotionDataService.update(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

	    @Test
	    public void test4_Insert() {
			HotelPromotionPO po = new HotelPromotionPO(2, 123456, "TEST|usernameNew", HotelPromotionType.BIRTHDAY_PROMOTION, new Date(1996-1900, 11-1, 21), 
	        		new Date(1996-1900, 11-1, 25), 0, "TEST|cooperation");
	        try {
	           hotelPromotionDataService.insert(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }
	    
	    @Test
	    public void test5_Get() {
	    	try {
	            ArrayList<HotelPromotionPO> list = hotelPromotionDataService.get(123456);
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
	        	ArrayList<HotelPromotionPO> list = hotelPromotionDataService.get(123456);
	            for(HotelPromotionPO po: list)
	            	hotelPromotionDataService.delete(po);
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }

}
