package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockCreditRecordBL;
import nju.quadra.hms.blservice.CreditRecordBLService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class CreditRecordBLTest {
    CreditRecordBLService creditRecordBL;

    @Before
    public void init() {
        creditRecordBL = new MockCreditRecordBL();
    }

    @Test
    public void testGet() {
        String username = "quadra";
        assertEquals(true, creditRecordBL.get(username).size() > 0);
    }

}
