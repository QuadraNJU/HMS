package nju.quadra.hms.po;

/**
 * Created by adn55 on 16/10/14.
 */
public class HotelPO {
    /**
     * 酒店ID
     */
    private int id;
    /**
     * 酒店名
     */
    private String name;
    /**
     * 商圈ID
     */
    private int areaId;
    /**
     * 地址
     */
    private String address;
    /**
     * 简介
     */
    private String description;
    /**
     * 设施服务
     */
    private String facilities;
    /**
     * 星级
     */
    private String star;
    /**
     * 工作人员用户名
     */
    private String staff;

    public HotelPO(int id, String name, int areaId, String address, String description, String facilities, String star, String staff) {
        this.id = id;
        this.name = name;
        this.areaId = areaId;
        this.address = address;
        this.description = description;
        this.facilities = facilities;
        this.star = star;
        this.staff = staff;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFacilities() {
        return facilities;
    }

    public void setFacilities(String facilities) {
        this.facilities = facilities;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getStaff() {
        return staff;
    }

    public void setStaff(String staff) {
        this.staff = staff;
    }
}
