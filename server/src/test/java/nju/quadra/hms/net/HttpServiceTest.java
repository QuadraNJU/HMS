package nju.quadra.hms.net;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by adn55 on 2016/11/23.
 */
public class HttpServiceTest {

    private HttpService httpService;

    @Test
    public void test() {
        int failedCount = 0;
        while (true) {
            int port = new Random().nextInt(55536) + 10000;
            httpService = new HttpService(port);
            try {
                httpService.start();
                break;
            } catch (IOException e) {
                e.printStackTrace();
                failedCount++;
                if (failedCount > 10) {
                    fail();
                }
            }
        }
        httpService.stop();
    }

}
