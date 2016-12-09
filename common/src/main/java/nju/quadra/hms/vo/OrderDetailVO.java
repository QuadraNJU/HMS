package nju.quadra.hms.vo;

import nju.quadra.hms.model.OrderState;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Created by adn55 on 2016/12/9.
 */
public class OrderDetailVO extends OrderVO {
    /**
     * 酒店
     */
    public final HotelVO hotel;
    /**
     * 客房
     */
    public final HotelRoomVO room;

    public OrderDetailVO(int id, String username, HotelVO hotel, LocalDate startDate, LocalDate endDate, HotelRoomVO room, int roomCount, ArrayList<String> persons, boolean hasChildren, double price, OrderState state, int rank, String comment) {
        super(id, username, hotel.id, startDate, endDate, room.id, roomCount, persons.size(), persons, hasChildren, price, state, rank, comment);
        this.hotel = hotel;
        this.room = room;
    }

    public String printRank() {
        String stars = "";
        for (int i = 0; i < rank; i++) {
            stars += "★";
        }
        for (int i = 0; i < 5-rank; i++) {
            stars += "☆";
        }
        return stars + "\n" + comment;
    }

}
