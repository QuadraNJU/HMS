package nju.quadra.hms.vo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by adn55 on 2016/12/6.
 */
public class HotelSearchVO extends HotelVO {
    /**
     * 客房列表
     */
    public ArrayList<HotelRoomVO> rooms;
    /**
     * 历史订单
     */
    public ArrayList<OrderVO> orders;
    /**
     * 客户评价
     */
    public ArrayList<OrderRankVO> ranks;

    public double getAverageRank() {
        try {
            return ranks.stream().mapToInt(rank -> rank.rank).average().getAsDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public double getMinPrice() {
        try {
            return rooms.stream().mapToDouble(room -> room.price).min().getAsDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public double getMaxPrice() {
        try {
            return rooms.stream().mapToDouble(room -> room.price).max().getAsDouble();
        } catch (Exception e) {
            return 0.0;
        }
    }

    public int getStar() {
        String[] stars = {"一星级", "二星级", "三星级", "四星级", "五星级"};
        int star = Arrays.binarySearch(stars, this.star);
        if (star >= 0) {
            return star+1;
        } else {
            return 0;
        }
    }

    public HotelSearchVO(int id, String name, int areaId, String address, String description, String facilities, String star, String staff) {
        super(id, name, areaId, address, description, facilities, star, staff);
    }

    public HotelSearchVO(HotelVO hotelVO, ArrayList<HotelRoomVO> rooms, ArrayList<OrderVO> orders, ArrayList<OrderRankVO> ranks) {
        super(hotelVO.id, hotelVO.name, hotelVO.areaId, hotelVO.address, hotelVO.description, hotelVO.facilities, hotelVO.star, hotelVO.staff);
        this.rooms = rooms;
        this.orders = orders;
        this.ranks = ranks;
    }

}
