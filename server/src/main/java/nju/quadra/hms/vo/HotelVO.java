package nju.quadra.hms.vo;

import nju.quadra.hms.po.HotelPO;

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
     * 城市ID
     */
    public int cityId;
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
     * 工作人员用户名
     */
    public String staff;

    public HotelVO(int id, String name, int cityId, int areaId, String address, String description, String facilities, String staff) {
        this.id = id;
        this.name = name;
        this.cityId = cityId;
        this.areaId = areaId;
        this.address = address;
        this.description = description;
        this.facilities = facilities;
        this.staff = staff;
    }

    public HotelVO(HotelPO po) {
        this(po.getId(), po.getName(), po.getCityId(), po.getAreaId(), po.getAddress(), po.getDescription(), po.getFacilities(), po.getStaff());
    }
}
