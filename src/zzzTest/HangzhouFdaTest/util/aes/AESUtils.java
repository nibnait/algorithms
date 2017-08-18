package zzzTest.HangzhouFdaTest.util.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class AESUtils {
    public AESUtils() {
    }

    public static String decrypt(String str, String decode, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        AES aes = new AES();
        return aes.decrypt(str, decode, key);
    }

    public static String encrypt(String str, String decode, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        AES aes = new AES();
        return aes.encrypt(URLEncoder.encode(str, decode), key);
    }

    public static String decrypt(String str, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, IOException {
        return decrypt(str, "GBK", key);
    }

    public static String encrypt(String str, String key) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        return encrypt(str, "UTF-8", key);
    }
}
