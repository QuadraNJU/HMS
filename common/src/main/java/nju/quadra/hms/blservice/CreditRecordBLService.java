package nju.quadra.hms.blservice;

import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;

public interface CreditRecordBLService {
    ArrayList<CreditRecordVO> get(String username);

    ResultMessage add(CreditRecordVO vo);

    ResultMessage topup(String username, int amount);
}
