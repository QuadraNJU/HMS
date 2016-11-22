package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockHotelPromotionBL;
import nju.quadra.hms.blservice.promotionBL.HotelPromotionBLService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelPromotionVO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HotelPromotionBLTest {
    HotelPromotionBLService hotelPromotionBL;
    @Before
    public void init() {
        hotelPromotionBL = new HotelPromotionBL();
    }

    @Test
    public void test1_Add() {
        ArrayList<String> co1 = new ArrayList<>();
        co1.add("TEST|coCom11");
        co1.add("TEST|coCom12");
        ArrayList<String> co2 = new ArrayList<>();
        co2.add("TEST|coCom21");
        co2.add("TEST|coCom22");
        HotelPromotionVO vo1 = new HotelPromotionVO(0, 123456, "TEST|name1", HotelPromotionType.TIME_PROMOTION,
                new Date(2016 - 1900, 11 - 1, 10 + 1), new Date(2016 - 1900, 11 - 1, 12 + 1), 0.8, co1);
        HotelPromotionVO vo2 = new HotelPromotionVO(0, 123456, "TEST|name2", HotelPromotionType.COMPANY_PROMOTION,
                new Date(2015 - 1900, 11 - 1, 10 + 1), new Date(2017 - 1900, 11 - 1, 12 + 1), 0.5, co2);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.add(vo1).result);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.add(vo2).result);
    }

    @Test
    public void test2_Modify() {
        ArrayList<HotelPromotionVO> voarr = hotelPromotionBL.get(123456);
        HotelPromotionVO vo = null;
        for(HotelPromotionVO tempvo: voarr) {
            if(tempvo.name.equals("TEST|name1")) {
                vo = tempvo;
                break;
            }
        }
        vo.name = "TEST|name11";
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.modify(vo).result);
    }

    @Test
    public void test3_Get() {
        ArrayList<HotelPromotionVO> voarr = hotelPromotionBL.get(123456);
        assertEquals(2, voarr.size());
        assertEquals("TEST|name11", voarr.get(0).name);
    }

    @Test
    public void test4_Delete() {
        ArrayList<HotelPromotionVO> voarr = hotelPromotionBL.get(123456);
        for(HotelPromotionVO vo: voarr) {
            assertEquals(ResultMessage.RESULT_SUCCESS, hotelPromotionBL.delete(vo.id).result);

        }
    }
}
