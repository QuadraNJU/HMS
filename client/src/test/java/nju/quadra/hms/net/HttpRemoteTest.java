package nju.quadra.hms.net;

import nju.quadra.hms.model.ResultMessage;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by adn55 on 2016/11/24.
 */
public class HttpRemoteTest {

    private HttpRemote remote;

    @Before
    public void init() {
        remote = new HttpRemote("UserBL");
    }

    @Test
    public void testLogin() {
        try {
            ResultMessage result = remote.invoke(ResultMessage.class, "login", "", "");
            assertNotNull(result);
            assertEquals(ResultMessage.RESULT_GENERAL_ERROR, result.result);
        } catch (IOException e) {
            // e.printStackTrace();
            System.out.println("Server not running, skipping this test");
        }
    }

}
