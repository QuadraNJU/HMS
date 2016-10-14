package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.HotelPromotionDataService;
import nju.quadra.hms.model.HotelPromotionType;
import nju.quadra.hms.po.HotelPromotionPO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelPromotionDataService_Stub implements HotelPromotionDataService {
    @Override
    public ArrayList<HotelPromotionPO> select(int hotelId) {
        ArrayList<HotelPromotionPO> list = new ArrayList<>();
        HotelPromotionPO po = new HotelPromotionPO(1, 1, "11.11优惠", HotelPromotionType.TIME_PROMOTION,
                new Date(2016, 11, 10), new Date(2016, 11, 12), -11.11, null);
        list.add(po);
        return list;
    }

    @Override
    public void insert(HotelPromotionPO po) {
        System.out.println("Insert HotelPromotionPO success");
    }

    @Override
    public void delete(HotelPromotionPO po) {
        System.out.println("Delete HotelPromotionPO success");
    }

    @Override
    public void update(HotelPromotionPO po) {
        System.out.println("Update HotelPromotionPO success");
    }
}
