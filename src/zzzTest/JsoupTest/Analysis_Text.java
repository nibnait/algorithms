package zzzTest.JsoupTest;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class Analysis_Text {
    private static final String LONG_TERM = "长期";

    private static final List<String> licenseNoList1 = Lists.newArrayList("统一社会信用代码", "社会统一信用代码", "企业信用代码");
    private static final List<String> licenseNoList2 = Lists.newArrayList("注册号");
    private static final List<String> corpNameList = Lists.newArrayList("名称", "企业名称", "名     称");
    private static final List<String> legalPersonList = Lists.newArrayList("法定代表人", "经营者姓名", "经营者", "经  营  者");
    private static final List<String> addressList = Lists.newArrayList("住所", "经营场所", "经 营 场 所");
    private static final List<String> expireDateList = Lists.newArrayList("营业期限至", "经营期限至");

    public static void main(String[] args) {
        String text = TestSourceText.HuBei;
        LicenseInfo licenseInfo = analysis_Textt(text);
        System.out.println(licenseInfo);
    }

    public static LicenseInfo analysis_Textt(String qcResult) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        String[] split1 = qcResult.split(";");
        if (split1.length>2) {
            System.out.println("split1----\n");
            return analysis_TextV1(split1);
        }
        //中文 分号
        String[] split2 = qcResult.split("；");
        if (split2.length>2) {
            System.out.println("split2----\n");
            return analysis_TextV1(split2);
        }
        // \n
        String[] split3 = qcResult.split("\\n");
        if (split3.length>2) {
            System.out.println("split3----\n");
            return analysis_TextV1(split3);
        }
        // ，
        String[] split4 = qcResult.split("，");
        if (split4.length>2) {
            System.out.println("split4----\n");
            return analysis_TextV1(split4);
        }

        return licenseInfo;
    }

    private static LicenseInfo analysis_TextV1(String[] split) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        for (int i = 0; i < split.length; i++) {
            String[] text = split[i].split(":");
            System.out.println("text[0]" + text[0]);
            if (text.length > 1) {
                System.out.println("text[1]" + text[1]);
            }
            String[] text_KongGe = split[i].split(": ");
            System.out.println("text_KongGe[0]" + text_KongGe[0]);
            if (text_KongGe.length > 1) {
                System.out.println("text_KongGe[1]" + text_KongGe[1]);
            }
            String[] text_ZhongWen = split[i].split("：");
            System.out.println("text_ZhongWen[0]" + text_ZhongWen[0]);
            if (text_ZhongWen.length > 1) {
                System.out.println("text_ZhongWen[1]" + text_ZhongWen[1]);
            }
            System.out.println("=================");
            if (text.length > 1) {
                licenseInfo = assigninValues(text, licenseInfo);
            } else if (text_ZhongWen.length > 1){
                //中文 分号
                licenseInfo = assigninValues(text_ZhongWen, licenseInfo);
            }
        }
        if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
            licenseInfo.setExpireDate(LONG_TERM);
        }
        return licenseInfo;
    }

    private static LicenseInfo assigninValues(String[] text, LicenseInfo licenseInfo) {
        licenseNoList1.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setLicenseNo(text[1].trim());
            }
        });
        licenseNoList2.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setLicenseNo(text[1].trim());
            }
        });
        corpNameList.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setCorpName(text[1].trim());
            }
        });
        legalPersonList.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setLegalPerson(text[1].trim());
            }
        });
        addressList.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setAddress(text[1].trim());
            }
        });
        expireDateList.stream().forEach(item -> {
            if (text[0].contains(item)) {
                licenseInfo.setExpireDate(text[1].trim());
            }
        });
        return licenseInfo;
    }


}
