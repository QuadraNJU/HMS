package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.CreditRecordPO;

import java.util.ArrayList;

/**
 * Created by adn55 on 16/10/15.
 */
public interface CreditDataService {
    ArrayList<CreditRecordPO> select(String username);

    void insert(CreditRecordPO po);
}
