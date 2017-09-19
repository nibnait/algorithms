package zzzTest.JsonParseTest;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class JsonParseMain {
    public static void main(String[] args) {

        String name = "满记甜品（M+购物中心店）";
        System.out.println(vertifyMerchantBaseName(name));

    }

    //审核门店名称
    private static List<String> vertifyMerchantBaseName(String name) {
        List<String> checkResult = new ArrayList<>();
        try {
            if (name == null) {
                return checkResult;
            }

            String all = "";
            List<RunshopAuditWordDto> allSensitiveWord = JSON.parseArray(all, RunshopAuditWordDto.class);
            StringBuilder protectedBrandStr = new StringBuilder();
            StringBuilder tendentiousWordStr = new StringBuilder();
            StringBuilder directiveWordStr = new StringBuilder();
            if (allSensitiveWord != null && allSensitiveWord.size() > 0) {
                for (RunshopAuditWordDto auditWord : allSensitiveWord) {
                    if (auditWord.getIsValid() == 1) {
                        if (auditWord.getWordType() == 1) {
                            protectedBrandStr.append(auditWord.getSensitiveWord()).append("|");
                            continue;
                        }
                        if (auditWord.getWordType() == 2) {
                            tendentiousWordStr.append(auditWord.getSensitiveWord()).append("|");
                            continue;
                        }
                        if (auditWord.getWordType() == 3) {
                            directiveWordStr.append(auditWord.getSensitiveWord()).append("|");
                            continue;
                        }
                    }
                }
                String protectedBrandString = protectedBrandStr.toString();
                String tendentiousWordString = tendentiousWordStr.toString();
                String directiveWordString = directiveWordStr.toString();
                System.out.println(protectedBrandString);
                if (Pattern.compile(protectedBrandString.substring(0, protectedBrandString.length() - 1)).matcher(name).find()) {
                    checkResult.add("ProtectBrand");
                }
                if (Pattern.compile(tendentiousWordString.substring(0, tendentiousWordString.length() - 1)).matcher(name).find()) {
                    checkResult.add("宣传性词");
                }
                if (Pattern.compile(directiveWordString.substring(0, directiveWordString.length() - 1)).matcher(name).find()) {
                    checkResult.add("方向性词");
                }
            }
        }catch (Exception e){
            e.printStackTrace();
//            logger.error("vertifyMerchantBaseName is error {}", e);
        }
        return checkResult;
    }
}
