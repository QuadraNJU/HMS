package nju.quadra.hms.dataservice;

import nju.quadra.hms.po.CreditRecordPO;

import java.util.ArrayList;

public interface CreditDataService {
    ArrayList<CreditRecordPO> get(String username) throws Exception;

    void insert(CreditRecordPO po) throws Exception;

    void delete(CreditRecordPO po) throws Exception;
}
