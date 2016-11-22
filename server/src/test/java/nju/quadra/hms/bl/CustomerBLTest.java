package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockCustomerBL;
import nju.quadra.hms.blservice.customerBL.CustomerBLService;
import nju.quadra.hms.model.MemberType;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.vo.MemberVO;
import org.junit.Before;
import org.junit.Test;


import java.util.Date;

import static org.junit.Assert.*;

/**
 * Created by admin on 2016/11/6.
 */
public class CustomerBLTest {
    CustomerBLService customerBL;

    @Before
    public void init() {
        customerBL = new MockCustomerBL();
    }

    @Test
    public void testGetInfo() {
        String username = "quadra";
        assertNotNull(customerBL.getInfo(username));
    }

    @Test
    public void testEnroll() {
        MemberVO vo = new MemberVO(MemberType.PERSONAL, new Date(1987, 2, 3), "Fate");
        assertEquals(ResultMessage.RESULT_SUCCESS, customerBL.enroll(vo).result);
    }
}
