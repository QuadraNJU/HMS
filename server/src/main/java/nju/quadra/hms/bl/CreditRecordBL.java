package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.data.mysql.CreditDataServiceImpl;
import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.po.CreditRecordPO;
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

    private static CreditDataService creditDataService;

    public CreditRecordBL(){
        creditDataService = new CreditDataServiceImpl();
    }

    @Override
    public ArrayList<CreditRecordVO> get(String username) {
        ArrayList<CreditRecordVO> voarr = new ArrayList<>();
        try {
            ArrayList<CreditRecordPO> poarr = creditDataService.get(username);
            for(CreditRecordPO po: poarr) voarr.add(CreditRecordBL.toVO(po));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return voarr;
    }

    @Override
    public ResultMessage add(CreditRecordVO vo) {
        CreditRecordPO po = CreditRecordBL.toPO(vo);
        try {
            creditDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }


    public static CreditRecordPO toPO(CreditRecordVO vo) {
        return new CreditRecordPO(vo.id, vo.username, vo.timestamp, vo.orderId, vo.action, vo.diff);
    }

    public static CreditRecordVO toVO(CreditRecordPO po) {
        double currCredit = ORIGINAL_CREDIT;
        try {
            ArrayList<CreditRecordPO> poarr = creditDataService.get(po.getUsername());
            if(!poarr.isEmpty()) {
                for(CreditRecordPO temppo: poarr) currCredit += temppo.getDiff();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new CreditRecordVO(po.getId(), po.getUsername(), po.getTimestamp(), po.getOrderId(), po.getAction(), po.getDiff(), currCredit);
    }
}
