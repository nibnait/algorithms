package zzzTest.HangzhouFdaTest.util.aes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class AESencrp {
    private String ALGO;
    private byte[] keyValue;

    public AESencrp() {
    }

    public String encrypt(String Data) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(1, key);
        byte[] encVal = c.doFinal(Data.getBytes("utf-8"));
        String encryptedValue = (new BASE64Encoder()).encode(encVal);
        return encryptedValue;
    }

    public String decrypt(String encryptedData, String bm) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        Key key = this.generateKey();
        Cipher c = Cipher.getInstance(this.ALGO);
        c.init(2, key);
        byte[] decordedValue = (new BASE64Decoder()).decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        if(bm == null) {
            bm = "GBK";
        }

        String decryptedValue = new String(decValue, bm);
        return decryptedValue;
    }

    private Key generateKey() {
        SecretKeySpec key = new SecretKeySpec(this.keyValue, this.ALGO);
        return key;
    }

    public String getALGO() {
        return this.ALGO;
    }

    public void setALGO(String aLGO) {
        this.ALGO = aLGO;
    }

    public byte[] getKeyValue() {
        return this.keyValue;
    }

    public void setKeyValue(byte[] keyValue) {
        this.keyValue = keyValue;
    }
}
