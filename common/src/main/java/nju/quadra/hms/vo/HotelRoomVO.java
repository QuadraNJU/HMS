package nju.quadra.hms.vo;

public class HotelRoomVO {
    /**
     * 客房ID
     */
    public final int id;
    /**
     * 酒店ID
     */
    public int hotelId;
    /**
     * 客房名称
     */
    public String name;
    /**
     * 总数量
     */
    public int total;
    /**
     * 原始价格
     */
    public double price;

    public HotelRoomVO(int id, int hotelId, String name, int total, double price) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.total = total;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + "（" + price + "/间）";
    }
}
