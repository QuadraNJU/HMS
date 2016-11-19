package nju.quadra.hms.bl.stub;

import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by adn55 on 16/10/15.
 */
public class CreditRecordBLService_Stub implements CreditRecordBLService {
    @Override
    public ArrayList<CreditRecordVO> get(String username) {
        ArrayList<CreditRecordVO> list = new ArrayList<>();
        CreditRecordVO vo = new CreditRecordVO(1, "quadra2", new Date(System.currentTimeMillis()), 1, CreditAction.ORDER_FINISHED, 99.9, 199.9);
        list.add(vo);
        return list;
    }

    @Override
    public ResultMessage add(CreditRecordVO vo) {
        return new ResultMessage(ResultMessage.RESULT_SUCCESS);
    }
}
