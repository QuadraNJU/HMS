package nju.quadra.hms.po;

public class AreaPO {

    private int id;

    private String cityName;

    private String areaName;

    public AreaPO(int id, String cityName, String areaName) {
        this.id = id;
        this.cityName = cityName;
        this.areaName = areaName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

}
