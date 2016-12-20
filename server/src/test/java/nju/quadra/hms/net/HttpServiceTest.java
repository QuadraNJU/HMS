package nju.quadra.hms.net;

import org.junit.Test;

import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.*;

public class HttpServiceTest {

    @Test
    public void test() {
        int failedCount = 0;
        HttpService httpService;
        while (true) {
            int port = new Random().nextInt(55536) + 10000;
            httpService = new HttpService(port);
            try {
                httpService.start();
                break;
            } catch (IOException e) {
                failedCount++;
                if (failedCount > 10) {
                    fail();
                }
            }
        }
        httpService.stop();
    }

}
