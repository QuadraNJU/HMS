package nju.quadra.hms.controller;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.net.BLServiceFactory;

/**
 * Created by adn55 on 2016/12/4.
 */
public class WebMarketerController {

    private CreditRecordBLService creditBL = BLServiceFactory.getCreditRecordBLService();

    public ResultMessage creditTopup(String username, int amount) {
        try {
            return creditBL.topup(username, amount);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResultMessage(ResultMessage.RESULT_NET_ERROR);
        }
    }

}
