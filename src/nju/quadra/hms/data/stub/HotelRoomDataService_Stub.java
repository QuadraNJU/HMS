package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.HotelRoomDataService;
import nju.quadra.hms.po.HotelRoomPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelRoomDataService_Stub implements HotelRoomDataService {
    @Override
    public ArrayList<HotelRoomPO> select(int hotelId) {
        ArrayList<HotelRoomPO> list = new ArrayList<>();
        HotelRoomPO po = new HotelRoomPO(1, 1, "大床房", 69, 129.9);
        list.add(po);
        return list;
    }

    @Override
    public void insert(HotelRoomPO po) {
        System.out.println("Insert HotelRoomPO success");
    }

    @Override
    public void delete(HotelRoomPO po) {
        System.out.println("Delete HotelRoomPO success");
    }

    @Override
    public void update(HotelRoomPO po) {
        System.out.println("Update HotelRoomPO success");
    }
}
