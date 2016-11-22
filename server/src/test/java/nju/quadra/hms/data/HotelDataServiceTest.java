package nju.quadra.hms.data;

import nju.quadra.hms.data.mysql.HotelDataServiceImpl;
import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.po.HotelPO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Created by RaUkonn on 2016/11/17.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelDataServiceTest {
    HotelDataService hotelDataService;

    @Before
    public void init() {
        hotelDataService = new HotelDataServiceImpl();
    }

    @Test
    public void test1_Insert() {
        HotelPO po = new HotelPO(0, "TEST|name", 12345, 67890, "TEST|address", "TEST|description", "TEST|facilities", "TEST|staff");
        HotelPO po1 = new HotelPO(0, "TEST|name1", 12345, 67890, "TEST|address1", "TEST|description1", "TEST|facilities1", "TEST|staff1");
        HotelPO po2 = new HotelPO(0, "TEST|name2", 455, 67890, "TEST|address2", "TEST|description2", "TEST|facilities2", "TEST|staff2");

        try {
            hotelDataService.insert(po);
            hotelDataService.insert(po1);
            hotelDataService.insert(po2);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test2_GetAll() {
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getAll();
            assertTrue(poarr.size() >= 3);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test3_GetById() {
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getAll();
            HotelPO po = hotelDataService.getById(poarr.get(0).getId());
            assertEquals(poarr.get(0).getAddress(), po.getAddress());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test4_Update() {
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getAll();
            HotelPO po = null;
            for(HotelPO po1: poarr) {
                if(po1.getName().equals("TEST|name1")) po = po1;
            }
            po.setAreaId(67890);
            hotelDataService.update(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test5_GetByArea() {
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getByArea(67890);
            assertEquals(3, poarr.size());
            assertEquals("TEST|name", poarr.get(0).getName());
            assertEquals("TEST|name1", poarr.get(1).getName());
            assertEquals("TEST|name2", poarr.get(2).getName());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test6_Delete() {
        try {
            ArrayList<HotelPO> poarr = hotelDataService.getByArea(67890);
            for (HotelPO po : poarr) {
                hotelDataService.delete(po);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }
}


