package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.AreaVO;
import nju.quadra.hms.vo.HotelSearchVO;
import nju.quadra.hms.vo.HotelVO;

import java.util.ArrayList;
/**
 * 实现酒店信息管理所需要的服务
 * 
 */
public interface HotelBLService {
	/**
	 * 搜索酒店信息
	 * @param areaId 地区编号
	 * @param username 用户名
	 * @return	该地区所有酒店值搜索对象的集合
	 */
    ArrayList<HotelSearchVO> search(int areaId, String username);
    /**
     * 通过地区编号搜索酒店信息
     * @param areaId 地区编号
     * @return	该地区所有酒店值对象的集合
     */
    ArrayList<HotelVO> getByArea(int areaId);
    /**
     * 工作人员获得酒店信息
     * @param staff 工作人员名称
     * @return 酒店工作人员对应酒店值对象
     */
    HotelVO getByStaff(String staff);
    /**
     * 通过酒店id获得酒店信息
     * @param id 酒店编号
     * @return 对应编号的酒店值对象
     */
    HotelVO getDetail(int id);
    /**
     * 获得所有地区信息
     * @return 所有地区信息值对象的集合
     */
    ArrayList<AreaVO> getAllArea();
    /**
     * 添加酒店信息
     * @param vo 酒店信息值对象
     * @return 结果消息
     */
    ResultMessage add(HotelVO vo);
    /**
     * 删除酒店信息
     * @param id 酒店编号
     * @return 结果消息
     */
    ResultMessage delete(int id);
    /**
     * 修改酒店信息
     * @param vo 酒店信息值对象
     * @return 结果消息
     */
    ResultMessage modify(HotelVO vo);
}
