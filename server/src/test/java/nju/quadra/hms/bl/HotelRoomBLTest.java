package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockHotelRoomBL;
import nju.quadra.hms.blservice.hotelRoomBL.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by admin on 2016/11/6.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelRoomBLTest {
    HotelRoomBLService hotelRoomBL;
    @Before
    public void init() {
        hotelRoomBL = new HotelRoomBL();
    }

    @Test
    public void test1_Add() {
        HotelRoomVO vo1 = new HotelRoomVO(0, 1, "大床房", 69, 129.9);
        HotelRoomVO vo2 = new HotelRoomVO(0, 1, "双人房", 35, 139.9);
        HotelRoomVO vo3 = new HotelRoomVO(0, 1, "双人房", 22, 169.9);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.add(vo2).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.add(vo3).result);
    }

    @Test
    public void test2_GetAll() {
       ArrayList<HotelRoomVO> voarr = hotelRoomBL.getAll(1);
       assertEquals(3, voarr.size());
       assertEquals(69, voarr.get(0).total);
       assertEquals(35, voarr.get(1).total);
       assertEquals(22,voarr.get(2).total);
    }

    @Test
    public void test3_Delete() {
    	ArrayList<HotelRoomVO> voarr = hotelRoomBL.getAll(1);
    	HotelRoomVO vo = voarr.get(0);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.delete(vo.id).result);
    }

    @Test
    public void testModify() {
    	ArrayList<HotelRoomVO> voarr = hotelRoomBL.getAll(1);
    	HotelRoomVO vo = voarr.get(0);
    	vo.name = "小床";
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.modify(vo.id, vo).result);
    }

}
