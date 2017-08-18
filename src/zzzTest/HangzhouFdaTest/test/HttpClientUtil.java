package zzzTest.HangzhouFdaTest.test;


import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import zzzTest.HangzhouFdaTest.util.SHAUtil;
import zzzTest.HangzhouFdaTest.util.aes.AESUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class HttpClientUtil {
    public HttpClientUtil() {
    }

    public static void main(String[] args) throws Exception {
        Long s = Long.valueOf(System.currentTimeMillis());
        HashMap map = new HashMap();
        String username = "sjxt";
        String password = "99999";
        String url = "https://demo.adlife.com.cn:85/food";
        String key = "IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRv";
        Long timestamp = Long.valueOf(System.currentTimeMillis());
        JSONObject $data = new JSONObject();
        $data.put("user", username);
        $data.put("pwd", password);
        $data.put("regno", "330102600113923");
        map.put("appKey", username);
        map.put("sign", getsign(username, password, timestamp));
        map.put("timestamp", "" + timestamp);
        System.out.println($data.toJSONString());
        map.put("data", AESUtils.encrypt($data.toJSONString(), "UTF-8", key));
        String pathurl = url + "/api/plat/queryent.html";
        String result = (new HttpClientUtil()).doPost(pathurl, map, "UTF-8");
        System.out.println(AESUtils.decrypt(result, key));
        System.out.println(URLDecoder.decode(AESUtils.decrypt(result, key), "UTF-8"));
        System.out.println(System.currentTimeMillis() - s.longValue());
    }

    public String doPost(String url, Map<String, String> map, String charset) {
        SSLClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;

        try {
            httpClient = new SSLClient();
            httpPost = new HttpPost(url);
            ArrayList ex = new ArrayList();
            Iterator iterator = map.entrySet().iterator();

            while(iterator.hasNext()) {
                Map.Entry response = (Map.Entry)iterator.next();
                ex.add(new BasicNameValuePair((String)response.getKey(), (String)response.getValue()));
            }

            if(ex.size() > 0) {
                UrlEncodedFormEntity response1 = new UrlEncodedFormEntity(ex, charset);
                httpPost.setEntity(response1);
            }

            HttpResponse response2 = httpClient.execute(httpPost);
            if(response2 != null) {
                HttpEntity resEntity = response2.getEntity();
                if(resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception var11) {
            var11.printStackTrace();
        }

        return result;
    }

    private static String getsign(String username, String password, Long timestamp) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        return SHAUtil.sha1(username + password + timestamp);
    }
}
