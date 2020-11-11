package org.tianbin.java.IO.classpath;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.tianbin.java.IO.FileReaderTest;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
 * Created by nibnait on 2020/11/12
 */
public class Main {

    @Test
    public void test01() throws IOException {
        String propertyFilePath = "/properties/default.properties";
        String jsonFilePath = "/config/rpc_config.json";

        InputStream inputStream = this.getClass().getResourceAsStream(jsonFilePath);
        String s = FileReaderTest.readFromInputStream(inputStream);
        System.out.println(s);

    }

    public static void main(String[] args) {
        RpcConfig rpcConfig = getRpcConfig("nevermore.service");
        System.out.println(JSON.toJSONString(rpcConfig));
    }

    public static RpcConfig getRpcConfig(String appId) {
        RpcConfig rpcConfig = new RpcConfig();
        String jsonFilePath = "/config/rpc_config.json";

        try {
            InputStream inputStream = new ResourcesReaderUtil().getResource(jsonFilePath);
            String rpcConfigJson = FileReaderTest.readFromInputStream(inputStream);

            HashMap hashMap = JSON.parseObject(rpcConfigJson, HashMap.class);
            Object config = hashMap.get(appId);

            rpcConfig = JSONObject.parseObject(JSON.toJSONBytes(config), RpcConfig.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rpcConfig;
    }

}
