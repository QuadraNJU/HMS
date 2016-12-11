package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.HotelBLService;
import nju.quadra.hms.data.mysql.HotelDataServiceImpl;
import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelVO;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/11/5.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelBLTest {
    private HotelBLService hotelBL;

    @Before
    public void init() {
        hotelBL = new HotelBL();
    }

    @Test
    public void test1_Delete() {
    	ArrayList<HotelVO> voarr = hotelBL.getAll();
    	for(HotelVO vo: voarr){
    		 assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.delete(vo.id).result);
    	}
    }
    
    @Test
    public void test2_Add() {
        HotelVO vo1 = new HotelVO(0, "南京抵抗军会议大酒店", 123456, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "四星级", "quadra");
        HotelVO vo2 = new HotelVO(0, "布达佩斯大饭店", 123456, "二战时候的布达佩斯（大概？）",
                "这是一个坐落于布达佩斯的大饭店", "我不知道这里有什么设施", "四星级", "quadra");
        HotelVO vo3 = new HotelVO(0, "南京金陵大饭店", 123456, "秦淮区汉中路汉中路2号",
                "金陵帝皇饭店", "应有尽有", "四星级", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.add(vo2).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.add(vo3).result);
    }

    @Test
    public void test3_GetAll() {
    	 ArrayList<HotelVO> voarr = hotelBL.getAll();
         assertEquals(3, voarr.size());
         assertEquals("南京抵抗军会议大酒店", voarr.get(0).name);
         assertEquals(123456, voarr.get(0).areaId);
    }

    @Test
    public void test4_Search() {
    	ArrayList<HotelVO> voarr = hotelBL.getByArea(123456);
    	assertEquals("南京抵抗军会议大酒店", voarr.get(0).name);
        assertEquals(123456, voarr.get(0).areaId);
        assertEquals("布达佩斯大饭店", voarr.get(1).name);
    }

    @Test
    public void test5_GetDetail() {
    	ArrayList<HotelVO> voarr = hotelBL.getAll();
    	HotelVO vo = voarr.get(0);
    	assertEquals("南京抵抗军会议大酒店", vo.name);
        assertEquals(123456, vo.areaId);
    }
    
    @Test
    public void test6_Modify() {
    	ArrayList<HotelVO> voarr = hotelBL.getAll();
    	HotelVO vo = voarr.get(0);
        vo.name = "金陵会议大酒店";
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.modify(vo).result);
    }
    
    @Test
    public void test8_Delete1() {
    	ArrayList<HotelVO> voarr = hotelBL.getByArea(123456);
    	for(HotelVO vo: voarr) assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.delete(vo.id).result);
    }


}
