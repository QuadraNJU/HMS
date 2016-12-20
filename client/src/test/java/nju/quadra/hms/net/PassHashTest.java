package nju.quadra.hms.net;

import nju.quadra.hms.util.PassHash;
import org.junit.Test;

import static org.junit.Assert.*;


public class PassHashTest {

    @Test
    public void testLatin() {
        assertEquals("a8a2f6ebe286697c527eb35a58b5539532e9b3ae3b64d4eb0a46fb657b41562c",
                PassHash.sha256("This is a test."));
    }

    @Test
    public void testCJK() {
        assertEquals("2c57141df466802cc11fd874783bd6dd221d05c4a352d66dcb159b0396927cd0",
                PassHash.sha256("这是一个测试。"));
    }

}
