package nju.quadra.hms.bl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import nju.quadra.hms.bl.mockObject.MockCustomerBL;
import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.MemberVO;

/**
 * Created by Ray on 16/11/5.
 */
public class CustomerBLTest {
	CustomerBLService customerBL;
	@Before
    public void init() {
        this.customerBL = new MockCustomerBL();
    }

    @Test
    public void testGetInfo() {
        assertNotNull(customerBL.getInfo("0"));
    }

    @Test
    public void testEnroll() {
        MemberVO vo=new  MemberVO(MemberType.PERSONAL, new Date(2016, 11, 12), "Quadra");
        assertEquals(ResultMessage.RESULT_SUCCESS, customerBL.enroll(vo).result);
    }

}
