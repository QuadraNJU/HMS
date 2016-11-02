package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.HotelPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface HotelDataService {
    ArrayList<HotelPO> getAll();

    HotelPO selectById(int id);

    ArrayList<HotelPO> selectByArea(int areaId);

    void insert(HotelPO po);

    void delete(HotelPO po);

    void update(HotelPO po);
}
