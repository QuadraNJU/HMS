package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.HotelDataService;
import nju.quadra.hms.po.HotelPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelDataService_Stub implements HotelDataService {
    private ArrayList<HotelPO> list;
    private HotelPO po;

    public HotelDataService_Stub() {
        list = new ArrayList<>();
        po = new HotelPO(1, "南京抵抗军会议大酒店", 1, 1, "玄武区中山陵四方城2号",
                "城中山林花园酒店", "各类客房, 大小会议室", "quadra");
        list.add(po);
    }

    @Override
    public ArrayList<HotelPO> getAll() {
        return list;
    }

    @Override
    public HotelPO getById(int id) {
        return po;
    }

    @Override
    public ArrayList<HotelPO> getByArea(int areaId) {
        return list;
    }

    @Override
    public void insert(HotelPO po) {
        System.out.println("Insert HotelPO success");
    }

    @Override
    public void delete(HotelPO po) {
        System.out.println("Delete HotelPO success");
    }

    @Override
    public void update(HotelPO po) {
        System.out.println("Update HotelPO success");
    }
}
