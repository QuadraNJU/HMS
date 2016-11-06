package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockHotelBL;
import nju.quadra.hms.blservice.hotelBL.HotelBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelVO;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by adn55 on 16/11/5.
 */
public class HotelBLTest {
    HotelBLService hotelBL;

    @Before
    public void init() {
        this.hotelBL = new MockHotelBL();
    }

    @Test
    public void testSearch() {
        assertEquals(true, hotelBL.search(0).size() > 0);
    }

    @Test
    public void testGetAll() {
        assertEquals(true, hotelBL.getAll().size() > 0);
    }

    @Test
    public void testGetDetail() {
        assertNotNull(hotelBL.getDetail(0));
    }

    @Test
    public void testAddDuplicate() {
        HotelVO vo = new HotelVO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.add(vo).result);
    }

    @Test
    public void testAddNormal() {
        HotelVO vo = new HotelVO(2, "布达佩斯大饭店", 2, 2, "二战时候的布达佩斯（大概？）",
                "这是一个坐落于布达佩斯的大饭店", "我不知道这里有什么设施", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.add(vo).result);
    }

    @Test
    public void testDelete() {
        HotelVO vo = new HotelVO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.delete(vo.id).result);
    }

    @Test
    public void testModify() {
        HotelVO vo = new HotelVO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.modify(vo.id, vo).result);
    }

    @Test
    public void testChangeStaff() {
        HotelVO vo = new HotelVO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelBL.changeStaff(vo.id, "hotelstaff").result);
    }
}
