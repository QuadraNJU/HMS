package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockHotelRoomBL;
import nju.quadra.hms.blservice.hotelRoomBL.HotelRoomBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.HotelRoomVO;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class HotelRoomBL {
    HotelRoomBLService hotelRoomBL;
    @Before
    public void init() {
        hotelRoomBL = new MockHotelRoomBL();
    }

    @Test
    public void testGetAll() {
        int hotelId = 1;
        assertEquals(true, hotelRoomBL.getAll(hotelId).size() >= 0);
    }

    @Test
    public void testAdd() {
        HotelRoomVO vo = new HotelRoomVO(1, 1, "大床房", 69, 129.9);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.add(vo).result);
    }

    @Test
    public void testDelete() {
        int roomId = 1;
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.delete(roomId).result);
    }

    @Test
    public void testModify() {
        int roomId = 1;
        HotelRoomVO vo = new HotelRoomVO(roomId, 1, "其实是小床房啦啦啦啦", 20, 100);
        assertEquals(ResultMessage.RESULT_SUCCESS, hotelRoomBL.modify(roomId, vo).result);
    }

}
