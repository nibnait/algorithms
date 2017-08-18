package zzzTest.wsdlTest;

//import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;
import java.security.MessageDigest;

public class EncryptUtil {

//    private static final Logger LOG = Logger.getLogger(EncryptUtil.class);
    private static final String ID_KEY = "pico12!@";

    /**
     * 通过SHA算法对srcStr进行编码（十六进制表示）
     * @param srcStr 需要获取MD5的字符串，不能为null
     * @return srcStr的SHA代码（40个字符）
     */
    public static String getSHADigest(String srcStr) {
        return getDigest(srcStr,"SHA-1");
    }

    /**
     * 获取srcStr的MD5编码（十六进制表示）
     * @param srcStr 需要获取MD5的字符串，不能为null
     * @return srcStr的MD5代码（32个字符）
     */
    public static String getMD5Digest(String srcStr) {
        return getDigest(srcStr,"MD5");
    }

    private static String getDigest(String srcStr,String alg) {
        try {
            MessageDigest alga = MessageDigest.getInstance(alg);
            alga.update(srcStr.getBytes());
            byte[] digesta = alga.digest();
            return byte2hex(digesta);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 转十六进制字符串
     * @param b
     * @return
     */
    private static String byte2hex(byte[] b) {
        StringBuffer hs = new StringBuffer();
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1){
                hs.append("0");
            }
            hs.append(stmp);
        }
        return hs.toString();
    }

    /**
     * 参数: @param message 待加密的字符串
     * 参数: @param key 密钥
     * 参数: @return
     * 描述: 加密
     */
    public static String encrypt(String message,String key){
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] result = cipher.doFinal(message.getBytes("UTF-8"));
            String cipherText = parseByte2HexStr(result);
            return cipherText;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 参数: @param message
     * 参数: @return
     * 描述: 采用默认密钥进行转换
     */
    public static String encrypt(String message){
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(ID_KEY.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(ID_KEY.getBytes("UTF-8"));
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
            byte[] result = cipher.doFinal(message.getBytes("UTF-8"));
            String cipherText = parseByte2HexStr(result);
            return cipherText;
        } catch (Exception e) {
//            LOG.error("DES加密失败",e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 参数: @param message 待加密的字符串
     * 参数: @param key 密钥
     * 参数: @return
     * 描述: 解密密
     * @throws Exception
     */
    public static String dencrypt(String message, String key){
        if (message == null || "".equals(message) || "".equals(message.trim()) || message.length() <= 0) {
            return null;
        }
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] bytes = parseHexStr2Byte(message);
            byte[] result = cipher.doFinal(bytes);
            return new String(result, "UTF-8");
        } catch (Exception e) {
//            LOG.error("DES解密失败 msg:"+message,e);
            System.out.println(e.getMessage());
            throw new RuntimeException("DES解密失败"+e.getMessage());
        }
    }

    /**
     * 参数: @param message
     * 参数: @return
     * 参数: @throws Exception
     * 描述: 采用默认密钥进行解码
     */
    public static String dencrypt(String message){
        try {
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
            DESKeySpec desKeySpec = new DESKeySpec(ID_KEY.getBytes("UTF-8"));
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            IvParameterSpec iv = new IvParameterSpec(ID_KEY.getBytes("UTF-8"));
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            byte[] bytes = parseHexStr2Byte(message);
            byte[] result = cipher.doFinal(bytes);
            return new String(result, "UTF-8");
        } catch (Exception e) {
//            LOG.error("DES解密失败 msg:"+message,e);
            System.out.println(e.getMessage());
        }
        return null;
    }

    /**
     * 将16进制转换为二进制
     */
    public static byte[] parseHexStr2Byte(String hexStr) {
        if (hexStr.length() < 1)
            return null;
        byte[] result = new byte[hexStr.length()/2];
        for (int i = 0;i< hexStr.length()/2; i++) {
            int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
            int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
            result[i] = (byte) (high * 16 + low);
        }
        return result;
    }

    /**
     * 将二进制转换成16进制
     */
    public static String parseByte2HexStr(byte buf[]) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void test(String[] args) {
        //        System.out.println(getMD5Digest("87834b9da6a01cf38cc37ccfbda52e4c"));
        String message = "<?xml version=\"1.0\" encoding=\"UTF8\"?><AuditLog><EVENT>"
                + "<shop_name>厦门市思明区料为康小吃店</shop_name>"
                + "<credit_no>350203810071084</credit_no>"
                + "<license_no>JY23502030023854</license_no><token>wangJian_xm$@</token></EVENT></AuditLog>";
        String str = encrypt(message, "meiya@!#");
        System.out.println(str);
        System.out.println(dencrypt(str,"meiya@!#"));
    }

}
