package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.WebsitePromotionPO;

import java.util.ArrayList;
/**
 * 负责网站促销策略管理的数据层服务
 *
 */
public interface WebsitePromotionDataService {
	/**
	 * 在数据库中获得WebsitePromotionPO对象
	 * @return 网络促销策略实例化对象集合
	 * @throws Exception 数据库访问异常
	 */
    ArrayList<WebsitePromotionPO> getAll() throws Exception;
    /**
     * 通过编号获得网络促销策略实例化对象
     * @param id 网络促销策略编号
     * @return 网络促销策略实例化对象
     * @throws Exception
     */
    WebsitePromotionPO getById(int id) throws Exception;
    /**
     * 在数据库中插入WebsitePromotionPO对象
     * @param po 网络促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void insert(WebsitePromotionPO po) throws Exception;
    /**
     * 在数据库中删除WebsitePromotionPO对象
     * @param po 网络促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void delete(WebsitePromotionPO po) throws Exception;
    /**
     * 在数据库中更新WebsitePromotionPO对象
     * @param po 网络促销策略实例化对象
     * @throws Exception 数据库访问异常
     */
    void update(WebsitePromotionPO po) throws Exception;
}
