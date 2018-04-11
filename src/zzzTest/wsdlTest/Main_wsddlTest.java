package zzzTest.wsdlTest;

import com.alibaba.fastjson.JSONObject;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;

public class Main_wsddlTest {
    public static void main(String[] args) {

//        int xiamenCityID = 13;
//        String wsdlURL = "http://xmwj.gov.cn:8080/gsadmin/webservice/GSWebService?wsdl";
//        String privateKey = "meiya@!#";
//        String token = "wangJian_xm$@";
//
//                JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
////                org.apache.cxf.endpoint.Client client = dcf.createClient(wsdlURL);
//                Object[] objects = new Object[0];
//        String message = "<?xml version=\"1.0\" encoding=\"UTF8\"?><AuditLog><EVENT>"
//                + "<shop_name>厦门市思明区料为康小吃店</shop_name>"
//                + "<credit_no>350203810071084</credit_no>"
//                + "<license_no>JY23502030023854</license_no><token>wangJian_xm$@</token></EVENT></AuditLog>";
//                String params = EncryptUtil.encrypt(message, privateKey);
//                try {
////                    objects = client.invoke("checkShopLicense", params);
//                } catch (Exception e) {
//
//                }
//                //输出调用结果
//                JSONObject jsonObject = JSONObject.parseObject(objects[0].toString());
//                Object result = jsonObject.get("result");
//                JSONObject resultObject = JSONObject.parseObject(result.toString());
//                VerifyResult verifyResult = null;
//                for (String key : resultObject.keySet()){
//                    verifyResult = checkVerifyCode(key);
//                }
//
//        System.out.println(verifyResult.toString());
    }
    private static VerifyResult checkVerifyCode(String verifyCode) {
        switch (verifyCode) {
            case "00":
                return new VerifyResult(VerifyResult.valid, "【资质有效】", VerifyResult.valid, "【资质有效】");
            case "01":
                return new VerifyResult(VerifyResult.blank, "--", VerifyResult.invalid, "【资质过期】");
            case "02":
                return new VerifyResult(VerifyResult.invalid, "【资质失效】", VerifyResult.blank, "--");
            case "03":
                return new VerifyResult(VerifyResult.invalid, "【单位名称查询无结果】", VerifyResult.blank, "--");
            case "04":
                return new VerifyResult(VerifyResult.invalid, "【号码错误】", VerifyResult.blank, "--");
            case "05":
                return new VerifyResult(VerifyResult.invalid, "【号码错误】", VerifyResult.blank, "--");
            case "06":
                return new VerifyResult(VerifyResult.blank, "--", VerifyResult.invalid, "【许可证号查询无结果】");
            case "07":
                return new VerifyResult(VerifyResult.invalid, "【两证不一致】", VerifyResult.invalid, "【两证不一致】");
            default:
                return null;
        }
    }
}
