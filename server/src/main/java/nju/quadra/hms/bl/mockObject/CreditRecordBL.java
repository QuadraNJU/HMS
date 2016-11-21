package nju.quadra.hms.bl.mockObject;

import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/21.
 */
public class CreditRecordBL implements CreditRecordBLService {
    //客户信用值相关信息
    public static final double ORIGINAL_CREDIT = 100.0;
    public static final double MIN_CREDIT = 0.0;
    public static final double[] UNDO_DELAYED_RATE = {-0.5, -1.0};
    public static final double UNDO_UNFINISHED_RATE = -0.5;
    public static final double FINISH_RATE = 1.0;
    public static final double RECHARGE_RATE = 100;
    public static final long LATEST_CHECKIN_TIME_GAP = 6 * (3600 * 1000);
    @Override
    public ArrayList<CreditRecordVO> get(String username) {
        return null;
    }

    @Override
    public ResultMessage add(CreditRecordVO vo) {
        return null;
    }
}
