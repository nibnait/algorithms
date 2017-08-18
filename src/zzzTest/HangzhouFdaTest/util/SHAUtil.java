package zzzTest.HangzhouFdaTest.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHAUtil {
    public SHAUtil() {
    }

    public static String sha1(String data) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(data.getBytes("UTF-8"));
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();

        for(int i = 0; i < bits.length; ++i) {
            int a = bits[i];
            if(a < 0) {
                a += 256;
            }

            if(a < 16) {
                buf.append("0");
            }

            buf.append(Integer.toHexString(a));
        }

        return buf.toString();
    }
}
