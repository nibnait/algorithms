package zzzTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;


public class Base64Convert {

    public static void main(String[] args) throws IOException {
        String fileBase64Code = getBaseCode();
        System.out.println(fileBase64Code);
    }

    /**
     * 将文件转化为字节数组字符串，并对其进行Base64编码处理
     * @return
     */
    private static String getBaseCode() throws IOException {
        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream("/Users/nibnait/Desktop/食安风险－店铺投诉2017.05.10.xlsx");
            data = new byte[in.available()];
            in.read(data);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
        // 返回Base64编码过的字节数组字符串
        return Base64.getEncoder().encodeToString(data);
    }
}
