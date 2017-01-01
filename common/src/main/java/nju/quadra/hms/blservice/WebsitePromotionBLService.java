package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.WebsitePromotionVO;

import java.util.ArrayList;
/**
 * 负责实现网站促销策略管理所需要的服务
 *
 */
public interface WebsitePromotionBLService {
	/**
	 * 获得所有网站促销策略
	 * @return 网站促销策略值对象的集合
	 */
    ArrayList<WebsitePromotionVO> get();
    /**
     * 添加网站促销策略
     * @param vo 网站促销策略值对象
     * @return 结果消息
     */
    ResultMessage add(WebsitePromotionVO vo);
    /**
     * 删除网站促销策略
     * @param promotionId 网站促销策略编号
     * @return 结果消息
     */
    ResultMessage delete(int promotionId);
    /**
     * 修改网站促销策略
     * @param vo 网站促销策略值对象
     * @return 结果消息
     */
    ResultMessage modify(WebsitePromotionVO vo);
}
