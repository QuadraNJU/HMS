package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelRoomPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelRoomDataService {
    ArrayList<HotelRoomPO> select(int hotelId);

    void insert(HotelRoomPO po);

    void delete(HotelRoomPO po);

    void update(HotelRoomPO po);
}
