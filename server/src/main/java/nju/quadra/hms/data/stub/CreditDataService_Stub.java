package nju.quadra.hms.data.stub;

import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.po.CreditRecordPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditDataService_Stub implements CreditDataService {
    @Override
    public ArrayList<CreditRecordPO> select(String username) {
        ArrayList<CreditRecordPO> list = new ArrayList<>();
        CreditRecordPO po = new CreditRecordPO(1, "quadra2", System.currentTimeMillis(), 1, CreditAction.ORDER_FINISHED, 99.9);
        list.add(po);
        return list;
    }

    @Override
    public void insert(CreditRecordPO po) {
        System.out.println("Insert CreditRecordPO success");
    }
}
