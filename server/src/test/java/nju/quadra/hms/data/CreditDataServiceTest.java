package nju.quadra.hms.data;

import nju.quadra.hms.data.mysql.CreditDataServiceImpl;
import nju.quadra.hms.data.mysql.MySQLManager;
import nju.quadra.hms.dataservice.CreditDataService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.po.CreditRecordPO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Created by RaUkonn on 2016/11/16.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreditDataServiceTest {
    private CreditDataService creditDataService;

    @Before
    public void init() {
        creditDataService = new CreditDataServiceImpl();
    }

    @Test
    public void test1_Insert() {
        CreditRecordPO po = new CreditRecordPO(0, "TEST|username", null, 123456, CreditAction.CREDIT_TOPUP, 99.9);
        try {
            creditDataService.insert(po);
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test2_Get() {
        try {
            ArrayList<CreditRecordPO> result = creditDataService.get("TEST|username");
            assertEquals(CreditAction.CREDIT_TOPUP, result.get(0).getAction());
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void test3_Delete() {
        try {
            ArrayList<CreditRecordPO> result = creditDataService.get("TEST|username");
            for (CreditRecordPO po : result) {
                creditDataService.delete(po);
            }
        } catch (Exception e) {
            e.printStackTrace();
            fail();
        }
    }

}
