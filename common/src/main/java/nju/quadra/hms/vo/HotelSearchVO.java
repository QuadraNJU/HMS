package nju.quadra.hms.vo;

import java.util.ArrayList;

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
