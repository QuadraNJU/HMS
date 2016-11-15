package nju.quadra.hms.data.driver;

import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.po.CreditRecordPO;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditDataService_Driver {
    public void drive(CreditDataService creditDataService) {
        try {
            creditDataService.get("quadra");
            creditDataService.insert(new CreditRecordPO(1, "quadra2", System.currentTimeMillis(), 1, CreditAction.ORDER_FINISHED, 99.9));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
