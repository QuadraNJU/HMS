package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
class CreditRecordController {
    private final CreditRecordBLService creditRecordBL;

    public CreditRecordController() {
        this.creditRecordBL = BLServiceFactory.getCreditRecordBLService();
    }

    public ArrayList<CreditRecordVO> get(String username) {
        try {
            return creditRecordBL.get(username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(CreditRecordVO vo) {
        try {
            return creditRecordBL.add(vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
