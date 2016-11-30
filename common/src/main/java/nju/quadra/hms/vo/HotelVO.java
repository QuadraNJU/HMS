package nju.quadra.hms.vo;

/**
 * Created by adn55 on 16/10/15.
 */
public class HotelVO {
    /**
     * 酒店ID
     */
    public int id;
    /**
     * 酒店名
     */
    public String name;
    /**
     * 商圈ID
     */
    public int areaId;
    /**
     * 地址
     */
    public String address;
    /**
     * 简介
     */
    public String description;
    /**
     * 设施服务
     */
    public String facilities;
    /**
     * 星级
     */
    public String star;
    /**
     * 工作人员用户名
     */
    public String staff;

    public HotelVO(int id, String name, int areaId, String address, String description, String facilities, String star, String staff) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.address = address;
        this.description = description;
        this.facilities = facilities;
        this.star = star;
        this.staff = staff;
    }
}
