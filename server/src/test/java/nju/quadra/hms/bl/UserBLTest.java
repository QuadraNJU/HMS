package nju.quadra.hms.bl;

import nju.quadra.hms.bl.mockObject.MockUserBL;
import nju.quadra.hms.blservice.userBL.UserBLService;
import nju.quadra.hms.model.ResultMessage;
import nju.quadra.hms.model.UserType;
import nju.quadra.hms.vo.UserVO;
import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

/**
 * Created by admin on 2016/11/6.
 */
public class UserBLTest {
    UserBLService userBL;

    @Before
    public void init() {
        this.userBL = new MockUserBL();
    }

    @Test
    public void testLogin() {
        String username = "havenothisusername";
        String password = "havenothispassword";
        assertEquals(ResultMessage.RESULT_ERROR, userBL.login(username, password).result);
    }

    @Test
    public void testGetAll() {
        assertEquals(true, userBL.getAll().size() >= 0);
    }

    @Test
    public void testGet() {
        String username = "qurdra";
        assertNotNull(userBL.get(username));
    }

    @Test
    public void testAdd() {
        UserVO vo = new UserVO("saber", "61d1e734315a0db52c3572d7ab8a8b68ac327bb42699ffd80b4fcec9870e77f3",
                "Servant", "110", UserType.WEBSITE_MANAGER, null, null, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.add(vo).result);
    }

    @Test
    public void testDelete() {
        String username = "qurdra";
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.delete(username).result);
    }

    @Test
    public void testModify() {
        String username = "qurdra";
        UserVO vo = new UserVO(username, "61d1e734315a0db52c3572d7ab8a8b68ac327bb42699ffd80b4fcec9870e77f3",
                "Quadra", "1008611", UserType.WEBSITE_MANAGER, null, null, null);
        assertEquals(ResultMessage.RESULT_SUCCESS, userBL.modify(username, vo).result);
    }

}
