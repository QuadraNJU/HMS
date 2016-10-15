package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.vo.MemberVO;
import nju.quadra.hms.vo.UserVO;

/**
 * Created by adn55 on 16/10/15.
 */
public class CustomerBLService_Driver {
    public void drive(CustomerBLService customerBLService) {
        try {
            UserVO vo = customerBLService.getInfo("quadra2");
            customerBLService.enroll(new MemberVO(MemberType.COMPANY, null, "广州市萌苗科技有限公司"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
