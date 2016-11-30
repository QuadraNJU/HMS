package nju.quadra.hms.controller;

import com.google.gson.reflect.TypeToken;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.HttpRemote;
import nju.quadra.hms.vo.CreditRecordVO;

import java.util.ArrayList;

/**
 * Created by RaUkonn on 2016/11/30.
 */
public class CreditRecordController {
    private HttpRemote creditRecordRemote;

    public CreditRecordController() {
        this.creditRecordRemote = new HttpRemote("CreditRecordBL");
    }

    public ArrayList<CreditRecordVO> get(String username) {
        try {
            return creditRecordRemote.invoke(new TypeToken<ArrayList<CreditRecordVO>>(){}.getType(), "get", username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ResultMessage add(CreditRecordVO vo) {
        try {
            return creditRecordRemote.invoke(ResultMessage.class, "add", vo);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
