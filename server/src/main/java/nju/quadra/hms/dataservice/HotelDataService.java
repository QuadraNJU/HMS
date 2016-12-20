package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.AreaPO;
import nju.quadra.hms.po.HotelPO;

import java.util.ArrayList;

public interface HotelDataService {
    ArrayList<HotelPO> getAll() throws Exception;

    HotelPO getById(int id) throws Exception;

    ArrayList<HotelPO> getByArea(int areaId) throws Exception;

    HotelPO getByStaff(String staff) throws Exception;

    ArrayList<AreaPO> getAllArea() throws Exception;

    void insert(HotelPO po) throws Exception;

    void delete(HotelPO po) throws Exception;

    void update(HotelPO po) throws Exception;
}
