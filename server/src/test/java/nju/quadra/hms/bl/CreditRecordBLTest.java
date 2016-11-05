package nju.quadra.hms.bl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import org.junit.Before;
import org.junit.Test;

import nju.quadra.hms.bl.mockObject.MockCreditRecordBL;
import nju.quadra.hms.blservice.customerBL.CreditRecordBLService;
import nju.quadra.hms.model.CreditAction;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.CreditRecordVO;
/**
 * Created by Ray on 16/11/5.
 */
public class CreditRecordBLTest {
      CreditRecordBLService crBL;
      @Before
      public void init() {
          this.crBL = new MockCreditRecordBL();
      }

      @Test
      public void testGet() {
          assertNotNull(crBL.get("0"));
      }

      @Test
      public void testAdd() {
    	  CreditRecordVO vo = new CreditRecordVO(1, "quadra2", System.currentTimeMillis(), 1, CreditAction.ORDER_FINISHED, 99.9, 199.9);
          assertEquals(ResultMessage.RESULT_SUCCESS, crBL.add(vo).result);
      }

}
