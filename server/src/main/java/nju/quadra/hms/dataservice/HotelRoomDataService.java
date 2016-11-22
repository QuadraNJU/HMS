package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelRoomPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelRoomDataService {
    ArrayList<HotelRoomPO> get(int hotelId) throws Exception;

    void insert(HotelRoomPO po) throws Exception;

    HotelRoomPO getById(int roomId) throws Exception;
    
    void delete(HotelRoomPO po) throws Exception;

    void update(HotelRoomPO po) throws Exception;
}
