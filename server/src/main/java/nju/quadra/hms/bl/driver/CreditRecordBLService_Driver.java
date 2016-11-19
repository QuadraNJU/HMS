package nju.quadra.hms.bl.driver;

import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordBLService_Driver {
    public void drive(CreditRecordBLService creditRecordBLService) {
        try {
            ArrayList<CreditRecordVO> list = creditRecordBLService.get("quadra");
            creditRecordBLService.add(new CreditRecordVO(1, "quadra2", new Date(System.currentTimeMillis()), 1, CreditAction.ORDER_FINISHED, 99.9, 199.9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
