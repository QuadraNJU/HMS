package nju.quadra.hms.po;

public class HotelRoomPO {
    /**
     * 客房ID
     */
    private int id;
    /**
     * 酒店ID
     */
    private int hotelId;
    /**
     * 客房名称
     */
    private String name;
    /**
     * 总数量
     */
    private int total;
    /**
     * 原始价格
     */
    private double price;

    public HotelRoomPO(int id, int hotelId, String name, int total, double price) {
        this.id = id;
        this.hotelId = hotelId;
        this.name = name;
        this.total = total;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
