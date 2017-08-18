package zzzTest.HangzhouFdaTest.util.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AES {
    static String spec = "9238513401340235";

    public AES() {
    }

    public String encrypt(String content, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        AESencrp aeSencrp = new AESencrp();
        aeSencrp.setALGO("AES");
        aeSencrp.setKeyValue(key.getBytes());
        return aeSencrp.encrypt(content);
    }

    public String decrypt(String src, String bm, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        AESencrp aeSencrp = new AESencrp();
        aeSencrp.setALGO("AES");
        aeSencrp.setKeyValue(key.getBytes());
        return aeSencrp.decrypt(src, bm);
    }

    public static void main(String[] args) throws Exception {
    }
}
