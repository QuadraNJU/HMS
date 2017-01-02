package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;

/**
 * 负责实现客户信用记录管理和信用值管理所需要的服务
 */
public interface CreditRecordBLService {
    /**
     * 获得用户的全部信用记录信息
     *
     * @param username 用户名
     * @return 用户所有信用记录值对象的集合
     */
    ArrayList<CreditRecordVO> get(String username);

    /**
     * 为用户增加信用值
     *
     * @param vo 用户信用记录信息值对象
     * @return 操作的结果消息
     */
    ResultMessage add(CreditRecordVO vo);

    /**
     * 为用户充值信用值
     *
     * @param username 用户名
     * @param amount   充值的金额
     * @return 操作的结果信息
     */
    ResultMessage topup(String username, int amount);
}
