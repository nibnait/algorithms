package zzzTest.JsoupTest;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import utils.JsonHelper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main_Jsoup {
    private static final String LONG_TERM = "长期";
    /**
     * 注册号
     */
    private static final String LicenseNo1 = "统一社会信用代码";
    private static final String LicenseNo2 = "注册号";
    private static final String LicenseNo_JiangSu = "REG_NO";
    private static final String LicenseNo_HeNan = "UNISCID";    //统一社会信用代码
    private static final List<String> licenseNoList = Lists.newArrayList("统一社会信用代码", "注册号");


    /**
     * 单位名称
     */
    private static final String CorpName1 = "名称";
    private static final String CorpName2 = "企业名称";
    private static final String CorpName_JiangSu = "CORP_NAME";
    private static final String CorpName_HeNan = "ENTNAME";
    private static final List<String> corpNameList = Lists.newArrayList("名称", "企业名称");

    /**
     * 法定代表人
     */
    private static final String LegalPerson1 = "法定代表人";
    private static final String LegalPerson2 = "经营者姓名";
    private static final String LegalPerson3 = "经营者";
    private static final String LegalPerson_JiangSu = "OPER_MAN_NAME";
    private static final String LegalPerson_HeNan = "LEREP";
    private static final List<String> legalPersonList = Lists.newArrayList("法定代表人", "经营者姓名", "经营者");

    /**
     * 注册地址
     */
    private static final String Address1 = "住所";
    private static final String Address2 = "经营场所";
    private static final String Address_JiangSu = "FARE_PLACE";
    private static final String Address_HeNan = "DOM";
    private static final List<String> addressList = Lists.newArrayList("住所", "经营场所");

    /**
     * 有效期
     */
    private static final String ExpireDate1 = "营业期限至";
    private static final String ExpireDate2 = "经营期限至";
    private static final String ExpireDate = "EXPIRE_DATE";
    private static final List<String> expireDateList = Lists.newArrayList("营业期限至", "经营期限至");

    /**
     * Common Constants
     */
    private static final String EMPTY = "";
    private static final String Tag_TD = "td";
    private static final String Tag_I = "i";
    private static final String Tag_Body = "body";
    private static final String GovV1_TableName = "tableYyzz";
    private static final String GovV7_TableName = "xinxi";
    private static final String GovV5_TagName = "table";

    private static final String GovV2_JiangSu_queryURL = "http://www.jsgsj.gov.cn:58888/ecipplatform/publicInfoQueryServlet.json?pageView=true&";
    private static final String GovV2_GuiZhou_queryURL = "http://gsxt.gzgs.gov.cn/business/JCXX.jspx";
    private static final String GovV4_HeNan_queryURL = "http://dzhy.haaic.gov.cn/yzt/ent/queryEntInfoById.do";
    private static final String GovV4_QingHai_queryURL = "http://218.95.241.36:8035/ent/queryEntInfoById.do";
    private static final String GovV4_AnHui_queryURL = "http://218.22.14.66:8082/yzt/ent/queryEntInfoById.do";
    private static final String GovV4_HaiNan_queryURL = "http://202.100.252.118:8079/yzt/ent/queryEntInfoById.do";
    private static final String GovV4_XiZang_queryURL = "http://220.182.3.99:9079/yztww/ent/queryEntInfoById.do ";

    public static void main(String[] args) {
        String url = TestSourceUrl.XiZang;
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(false);

        /**
         * 直接爬网页，根据tableName/tagName 获取
         */
        if (url.contains(LicenseUrlSource.BeiJing.getUrl())) {
            licenseInfo = BeiJingAnalysis(url);
        }
        if (url.contains(LicenseUrlSource.HeBei.getUrl())
                || url.contains(LicenseUrlSource.ShangHai.getUrl())
                || url.contains(LicenseUrlSource.HuNan.getUrl())
                || url.contains(LicenseUrlSource.YunNan.getUrl())
                || url.contains(LicenseUrlSource.SiChuan.getUrl())) {
            licenseInfo = GovAnalysis_V1(url);
        }
        if (url.contains(LicenseUrlSource.GuangDong.getUrl())) {
            licenseInfo = GovAnalysis_V5_GuangDong(url);
        }

        /**
         * 需转换url，然后发Get请求
         */
        //返回的是html
        if (url.contains(LicenseUrlSource.JiangSu.getUrl())) {
            licenseInfo = GovAnalysis_V2_JiangSu(url);
        }
        //先转换url，从返回的<body>html中 根据tableName, 来获取
        if (url.contains(LicenseUrlSource.GuiZhou.getUrl())) {
            licenseInfo = GovAnalysis_V7_GuiZhou(url);
        }
        //返回的是Json
        if (url.contains(LicenseUrlSource.HeNan.getUrl())) {
            licenseInfo = GovAnalysis_V4(url, GovV4_HeNan_queryURL);
        }
        if (url.contains(LicenseUrlSource.QingHai.getUrl())) {
            licenseInfo = GovAnalysis_V4(url, GovV4_QingHai_queryURL);
        }
        if (url.contains(LicenseUrlSource.AnHui.getUrl())) {
            licenseInfo = GovAnalysis_V4(url, GovV4_AnHui_queryURL);
        }
        if (url.contains(LicenseUrlSource.HaiNan.getUrl())) {
            licenseInfo = GovAnalysis_V4(url, GovV4_HaiNan_queryURL);
        }
        if (url.contains(LicenseUrlSource.XiZang.getUrl())) {
            licenseInfo = GovAnalysis_V4(url, GovV4_XiZang_queryURL);
        }

        /**
         * 待破解
         */
        if (url.contains(LicenseUrlSource.JiangXi.getUrl())) {
            licenseInfo = GovAnalysis_V3_JiangXi(url);
        }
        if (!licenseInfo.isSupport()) {
            licenseInfo = Analysis_Text.analysis_Textt(url);
        }
        System.out.println(licenseInfo);
//            return licenseInfo;
    }

    public static LicenseInfo GovAnalysis_V4(String url, String queryURL) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        String[] split = url.split("id=");
        if (split.length>1) {
            String str = split[1].substring(0, split[1].indexOf("&"));
            String param = "page=1&pageSize=1&str="+str;
            try {
                System.out.println(queryURL);
                System.out.println(param);
                String response = JavaNetApi.sendGet(queryURL, param);
                if (StringUtils.isNotBlank(response)) {
                    Map<String, String> object = JsonHelper.toJsonObject(response, Map.class);
                    if (object.get(LicenseNo_HeNan) != null) {
                        licenseInfo.setLicenseNo(object.get(LicenseNo_HeNan).trim());
                    }
                    if (object.get(CorpName_HeNan) != null) {
                        licenseInfo.setCorpName(object.get(CorpName_HeNan).trim());
                    }
                    if (object.get(LegalPerson_HeNan) != null) {
                        licenseInfo.setLegalPerson(object.get(LegalPerson_HeNan).trim());
                    }
                    if (object.get(Address_HeNan) != null) {
                        licenseInfo.setAddress(object.get(Address_HeNan).trim());
                    }
                    if (object.get(ExpireDate) != null) {
                        licenseInfo.setExpireDate(object.get(ExpireDate).trim());
                    }
                    if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                        licenseInfo.setExpireDate(LONG_TERM);
                    }
                }
            } catch (IOException e) {
//                logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
            }
        }
        return licenseInfo;
    }

    public static LicenseInfo GovAnalysis_V3_JiangXi(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        try {
            Document document = org.jsoup.Jsoup.connect(url).get();
            Elements tableYyzz = document.getElementsByTag(Tag_Body);
            if (tableYyzz.size() > 0) {
                String str = tableYyzz.get(0).toString();
                String substring = str.substring(8, str.length()-8);
                Map<String, String> map = JsonHelper.toJsonObject(substring, Map.class);

            }
            if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                licenseInfo.setExpireDate(LONG_TERM);
            }
        } catch (IOException e) {
            //logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    public static LicenseInfo GovAnalysis_V5_GuangDong(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        try {
            Document document = org.jsoup.Jsoup.connect(url).get();
            Elements tableYyzz = document.getElementsByTag(GovV5_TagName);
            if (tableYyzz.size() > 0) {
                Element element = tableYyzz.get(0);
                Elements td = element.getElementsByTag(Tag_TD);
                for (int i = 0; i < td.size(); i++) {
                    String text = td.get(i).text().trim();
                    licenseNoList.stream().forEach(item -> {
                        if (text.contains(item)) {
                            licenseInfo.setLicenseNo(getTextInfo(text));
                        }
                    });
                    corpNameList.stream().forEach(item -> {
                        if (text.contains(item)) {
                            licenseInfo.setCorpName(getTextInfo(text));
                        }
                    });
                    legalPersonList.stream().forEach(item -> {
                        if (text.contains(item)) {
                            licenseInfo.setLegalPerson(getTextInfo(text));
                        }
                    });
                    addressList.stream().forEach(item -> {
                        if (text.contains(item)) {
                            licenseInfo.setAddress(getTextInfo(text));
                        }
                    });
                    expireDateList.stream().forEach(item -> {
                        if (text.contains(item)) {
                            licenseInfo.setExpireDate(getTextInfo(text));
                        }
                    });
                }
                if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                    licenseInfo.setExpireDate(LONG_TERM);
                }
            }
        } catch (IOException e) {
            //logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    private static String getTextInfo(String text) {
        String[] split = text.split("：");
        if (split.length>1) {
            return split[1];
        }
        return EMPTY;
    }

    /**
     * 贵州
     * @param url
     * @return
     */
    public static LicenseInfo GovAnalysis_V7_GuiZhou(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        String queryURL = GovV2_GuiZhou_queryURL + url.substring(url.indexOf("?"), url.indexOf("&"));
        try {
            Document document = org.jsoup.Jsoup.connect(queryURL).get();
            Elements bodyElements = document.getElementsByTag(Tag_Body);
            if (bodyElements.size() > 0) {
                Element bodyElement = bodyElements.get(0);
                Elements tableYyzz = bodyElement.getElementsByClass(GovV7_TableName);
                if (tableYyzz.size() > 0) {
                    Element element = tableYyzz.get(0);
                    Elements td = element.getElementsByTag(Tag_TD);
                    for (int i = 0; i < td.size(); i++) {
                        String text = td.get(i).text().trim();
                        licenseNoList.stream().forEach(item -> {
                            if (text.contains(item)) {
                                licenseInfo.setLicenseNo(getTextInfo(text));
                            }
                        });
                        corpNameList.stream().forEach(item -> {
                            if (text.contains(item)) {
                                licenseInfo.setCorpName(getTextInfo(text));
                            }
                        });
                        legalPersonList.stream().forEach(item -> {
                            if (text.contains(item)) {
                                licenseInfo.setLegalPerson(getTextInfo(text));
                            }
                        });
                        addressList.stream().forEach(item -> {
                            if (text.contains(item)) {
                                licenseInfo.setAddress(getTextInfo(text));
                            }
                        });
                        expireDateList.stream().forEach(item -> {
                            if (text.contains(item)) {
                                licenseInfo.setExpireDate(getTextInfo(text));
                            }
                        });
                    }
                    if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                        licenseInfo.setExpireDate(LONG_TERM);
                    }
                }

            }
        } catch (Exception e) {
            //logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    /**
     * 江苏
     * @param url
     * @return
     */
    public static LicenseInfo GovAnalysis_V2_JiangSu(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        int index = url.indexOf("?");
        String queryURL = GovV2_JiangSu_queryURL + url.substring(index+1, url.length());
        try {
            Document document = org.jsoup.Jsoup.connect(queryURL).get();
            Elements tableYyzz = document.getElementsByTag(Tag_Body);
            if (tableYyzz.size() > 0) {
                Element element = tableYyzz.get(0);
                String str = element.toString();
                int bannerHtml = str.indexOf("bannerHtml");
                String substring = str.substring(8, bannerHtml-2);
                substring += "}";
                Map<String, String> object = JsonHelper.toJsonObject(substring, Map.class);
                if (object.get(LicenseNo_JiangSu) != null) {
                    licenseInfo.setLicenseNo(object.get(LicenseNo_JiangSu).trim());
                }
                if (object.get(CorpName_JiangSu) != null) {
                    licenseInfo.setCorpName(object.get(CorpName_JiangSu).trim());
                }
                if (object.get(LegalPerson_JiangSu) != null) {
                    licenseInfo.setLegalPerson(object.get(LegalPerson_JiangSu).trim());
                }
                if (object.get(Address_JiangSu) != null) {
                    licenseInfo.setAddress(object.get(Address_JiangSu).trim());
                }
                if (object.get(ExpireDate) != null) {
                    licenseInfo.setExpireDate(object.get(ExpireDate).trim());
                }
                if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                    licenseInfo.setExpireDate(LONG_TERM);
                }
            }
        } catch (Exception e) {
//                logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    /**
     * 河北、上海、湖南
     * @param url
     * @return
     */
    public static LicenseInfo GovAnalysis_V1(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        try {
            Document document = org.jsoup.Jsoup.connect(url).get();
            Elements tableYyzz = document.getElementsByClass(GovV1_TableName);
            if (tableYyzz.size() > 0) {
                Element element = tableYyzz.get(0);
                Elements td = element.getElementsByTag(Tag_TD);
                for (int i = 0; i < td.size(); i++) {
                    String text = td.get(i).text();
                    if (text.contains(LicenseNo1)) {
                        licenseInfo.setLicenseNo(td.get(i).getElementsByTag(Tag_I).text().trim());
                        continue;
                    }
                    if (text.contains(CorpName2)) {
                        licenseInfo.setCorpName(td.get(i).getElementsByTag(Tag_I).text().trim());
                        continue;
                    }
                    if (text.contains(LegalPerson1) || text.contains(LegalPerson3)) {
                        licenseInfo.setLegalPerson(td.get(i).getElementsByTag(Tag_I).text().trim());
                        continue;
                    }
                    if (text.contains(Address1) || text.contains(Address2)) {
                        licenseInfo.setAddress(td.get(i).getElementsByTag(Tag_I).text().trim());
                        continue;
                    }
                    if (text.contains(ExpireDate1) || text.contains(ExpireDate2)) {
                        licenseInfo.setExpireDate(td.get(i).getElementsByTag(Tag_I).text().trim());
                        continue;
                    }
                }
                if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                    licenseInfo.setExpireDate(LONG_TERM);
                }
            }
        } catch (Exception e) {
//                logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    public static LicenseInfo BeiJingAnalysis(String url) {
        LicenseInfo licenseInfo = LicenseInfo.newLicenseInfoBySupport(true);
        try {
            Document document = Jsoup.connect(url).get();
            Elements dl = document.getElementsByTag("dl");
            Element element = dl.get(0);
            Elements dt = element.getElementsByTag("dt");
            Elements dd = element.getElementsByTag("dd");
            int cursor = 0; //游标
            for (int i = 0; i < dt.size(); i++) {
                if (dt.get(i).text().equals(LicenseNo1)
                        || dt.get(i).text().equals(LicenseNo2)) {
                    if (dd.get(i+1).text().contains("注")) {
                        cursor++;
                        licenseInfo.setLicenseNo(getLicenseNo(dd.get(i+cursor).text().trim()));
                    } else {
                        licenseInfo.setLicenseNo(dd.get(i).text().trim());
                    }
                    continue;
                }
                if (dt.get(i).text().equals(CorpName1)) {
                    licenseInfo.setCorpName(dd.get(i+cursor).text().trim());
                    continue;
                }
                if (dt.get(i).text().equals(LegalPerson1)
                        || dt.get(i).text().equals(LegalPerson2)
                        || dt.get(i).text().equals(LegalPerson3)) {
                    licenseInfo.setLegalPerson(dd.get(i+cursor).text().trim());
                    continue;
                }
                if (dt.get(i).text().equals(Address1)
                        || dt.get(i).text().equals(Address2)) {
                    licenseInfo.setAddress(dd.get(i+cursor).text().trim());
                    continue;
                }
                if (dt.get(i).text().equals(ExpireDate1)) {
                    licenseInfo.setExpireDate(dd.get(i+cursor).text().trim());
                    continue;
                }
            }
            if (StringUtils.isBlank(licenseInfo.getExpireDate())) {
                licenseInfo.setExpireDate(LONG_TERM);
            }
        } catch (Exception e) {
//            logger.error("AnalysizeLicenseHelper.BeiJingAnalysis error, url:%s, msg:%s", url, e.getMessage());
        }
        return licenseInfo;
    }

    private static String getLicenseNo(String text) {
        char[] chars = text.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if (Character.isDigit(chars[i])) {
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }
}