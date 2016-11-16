package nju.quadra.hms.data;

import nju.quadra.hms.data.mysql.HotelRoomDataServiceImpl;
import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.CreditRecordPO;
import nju.quadra.hms.po.HotelRoomPO;
import nju.quadra.hms.po.UserPO;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelRoomDataServiceTest {
	private HotelRoomDataService hotelRoomDataService;
	
	@Before
	public void init(){
		hotelRoomDataService = new HotelRoomDataServiceImpl();
	}
	
	@Test
	public void test1_Insert(){
		HotelRoomPO po = new HotelRoomPO(0, 0 , "TEST|hotelroomname", 123456, 1.0);
        try {
            hotelRoomDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
	}
	
	@Test
    public void test2_Get() {
        try {
            ArrayList<HotelRoomPO> result = hotelRoomDataService.get(0);
            assertEquals("TEST|hotelroomname", result.get(0).getName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
	
	@Test
    public void test3_Update() {
		HotelRoomPO po = new HotelRoomPO(0, 1 , "TEST|hotelroomname", 123456, 1.0);
        try {
        	hotelRoomDataService.update(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
	
	@Test
    public void test4_Get() {
        try {
            ArrayList<HotelRoomPO> result = hotelRoomDataService.get(0);
            assertEquals(1, result.get(0).getHotelId());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
	
	 @Test
	    public void test5_Delete() {
	        try {
	            ArrayList<HotelRoomPO> result =  hotelRoomDataService.get(0);
	            for (HotelRoomPO po : result) {
	            	hotelRoomDataService.delete(po);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            fail();
	        }
	    }
	
}
