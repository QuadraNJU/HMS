package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.data.mysql.CreditDataServiceImpl;
import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.po.CreditRecordPO;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.UserVO;

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
    public static final long LATEST_CHECKIN_TIME_GAP = 6;

    private CreditDataService creditDataService;
    private UserBLService userBL;

    public CreditRecordBL(){
        creditDataService = new CreditDataServiceImpl();
        userBL = new UserBL();
    }

    @Override
    public ArrayList<CreditRecordVO> get(String username) {
        ArrayList<CreditRecordVO> voarr = new ArrayList<>();
        try {
            ArrayList<CreditRecordPO> poarr = creditDataService.get(username);
            double creditResult = ORIGINAL_CREDIT;
            for (int i = poarr.size()-1; i >= 0; i--) {
                CreditRecordPO po = poarr.get(i);
                creditResult += po.getDiff();
                // reverse
                voarr.add(0, new CreditRecordVO(po.getId(), po.getUsername(), po.getTimestamp(), po.getOrderId(), po.getAction(), po.getDiff(), creditResult));
            }
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

    @Override
    public ResultMessage topup(String username, int amount) {
        try {
            UserVO user = userBL.get(username);
            if (user == null) {
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "该用户名不存在，请重新输入");
            }
            if (!user.type.equals(UserType.CUSTOMER)) {
                return new ResultMessage(ResultMessage.RESULT_GENERAL_ERROR, "该用户不是客户类型，无法充值");
            }
            return add(new CreditRecordVO(0, username, null, 0, CreditAction.CREDIT_TOPUP, amount*RECHARGE_RATE, 0));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_DB_ERROR);
        }
    }


    private static CreditRecordPO toPO(CreditRecordVO vo) {
        return new CreditRecordPO(0, vo.username, null, vo.orderId, vo.action, vo.diff);
    }

}
