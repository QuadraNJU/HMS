package nju.quadra.hms.bl;

import nju.quadra.hms.blservice.CreditRecordBLService;
import nju.quadra.hms.blservice.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.util.PassHash;
import nju.quadra.hms.vo.CreditRecordVO;
import nju.quadra.hms.vo.UserVO;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by adn55 on 2016/12/10.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CreditRecordBLTest {
    private CreditRecordBLService creditBL;
    private UserBLService userBL;

    @Before
    public void init() {
        creditBL = new CreditRecordBL();
        userBL = new UserBL();
    }

    @Test
    public void test1_addUser() {
        userBL.delete("TEST_customer");
        ResultMessage addResult = userBL.add(new UserVO("TEST_customer", PassHash.hash("test"), "测试客户", "test", UserType.CUSTOMER));
        assertEquals(ResultMessage.RESULT_SUCCESS, addResult.result);
    }

    @Test
    public void test2_getCreditRecord() {
        ArrayList<CreditRecordVO> credits = creditBL.get("TEST_customer");
        assertEquals(100.0, credits.get(credits.size() - 1).diff, 0.01);
        assertEquals(100.0, credits.get(credits.size() - 1).creditResult, 0.01);
    }

    @Test
    public void test3_topup() {
        ArrayList<CreditRecordVO> credits = creditBL.get("TEST_customer");
        double creditBefore = credits.get(0).creditResult;
        ResultMessage result = creditBL.topup("TEST_customer", 10);
        assertEquals(ResultMessage.RESULT_SUCCESS, result.result);
        credits = creditBL.get("TEST_customer");
        assertEquals(10 * CreditRecordBL.RECHARGE_RATE, credits.get(0).diff, 0.01);
        assertEquals(creditBefore + 10 * CreditRecordBL.RECHARGE_RATE, credits.get(0).creditResult, 0.01);
    }

    @Test
    public void test4_topupInvalid() {
        ResultMessage result = creditBL.topup("TEST_customer_invalid", 10);
        assertEquals(ResultMessage.RESULT_GENERAL_ERROR, result.result);
    }

}
