package zzzTest.JsoupTest;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Jsoup {

    private static final String CORP_NAME = "企业名称";
    private static final String LEGAL_PERSON = "经营者";
    private static final String LEGAL_PERSON2 = "法定代表人";
    private static final String REGIST_NUMBER = "统一社会信用代码";
    private static final String REGIST_ADDRESS = "住所";
    private static final String REGIST_ADDRESS2 = "经营场所";
    private static final String EXPIRE_DATE = "营业期限至";
    private static final String EXPIRE_DATE2 = "经营期限至";


    public static void main(String[] args) {
        try {
            String HeBeiUrl2 = "http://www.hebscztxyxx.gov.cn/noticehb/notice/view?uuid=E_qAUjuf7CB8BG8lk9IKyiNWKtlKMG1o";
            String HeBeiUrl = "http://www.hebscztxyxx.gov.cn/noticehb/notice/view?uuid=UG5KbQC.TvMgx8G6IO7C_MIhcIe.QQSX";   //河北 √
            String HuNanUrl = "http://gsxt.hnaic.gov.cn/notice/notice/view?uuid=ZuhxbApOrkPXPR3bxEE8RZ37uEYNBooo";      //湖南 有验证码拦截
            String ShanxiUrl = "http://sx.gsxt.gov.cn/index.jspx/EntInfo.jspx?id=YmZrbG9xcXVyd3Fzc21zZW9n";             //山西（识别出来的是文本）    404
            String ShangHaiUrl = "http://www.sgs.gov.cn/notice/notice/view?uuid=9nqL_YzklLDteKGSu6GKAg==";

            Document document = org.jsoup.Jsoup.connect(HuNanUrl).get();
            //获取网页源码文本内容
            System.out.println(document.toString());
            LicenseInfo license = new LicenseInfo();

            Elements tableYyzz = document.getElementsByClass("tableYyzz");
            if (tableYyzz.size()>0) {
                Element element = tableYyzz.get(0);
                Elements td = element.getElementsByTag("td");
                for (int i = 0; i < td.size(); i++) {
                    String text = td.get(i).text();
                    if (text.contains(CORP_NAME)) {
                        license.setCorpName(td.get(i).getElementsByTag("i").text());
                        continue;
                    }
                    if (text.contains(LEGAL_PERSON) || text.contains(LEGAL_PERSON2)) {
                        license.setLegalPerson(td.get(i).getElementsByTag("i").text());
                        continue;
                    }
                    if (text.contains(REGIST_NUMBER)) {
                        license.setLicenseNo(td.get(i).getElementsByTag("i").text());
                        continue;
                    }
                    if (text.contains(REGIST_ADDRESS) || text.contains(REGIST_ADDRESS2)) {
                        license.setAddress(td.get(i).getElementsByTag("i").text());
                        continue;
                    }
                    if (text.contains(EXPIRE_DATE) || text.contains(EXPIRE_DATE2)) {
                        license.setExpireDate(td.get(i).getElementsByTag("i").text());
                        continue;
                    }
                }
                System.out.println(license.toString());
            }

        } catch (IOException e) {
            System.out.println("解析出错！");
            e.printStackTrace();
        }

    }

    static class LicenseInfo {
        private String corpName;
        private String legalPerson;
        private String licenseNo;
        private String address;
        private String expireDate;

        public String getCorpName() {
            return corpName;
        }

        public void setCorpName(String corpName) {
            this.corpName = corpName;
        }

        public String getLegalPerson() {
            return legalPerson;
        }

        public void setLegalPerson(String legalPerson) {
            this.legalPerson = legalPerson;
        }

        public String getLicenseNo() {
            return licenseNo;
        }

        public void setLicenseNo(String licenseNo) {
            this.licenseNo = licenseNo;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExpireDate() {
            return expireDate;
        }

        public void setExpireDate(String expireDate) {
            this.expireDate = expireDate;
        }

        @Override
        public String toString() {
            return "License{" +
                    "corpName='" + corpName + '\'' +
                    ", legalPerson='" + legalPerson + '\'' +
                    ", registNum='" + licenseNo + '\'' +
                    ", registAiTagress='" + address + '\'' +
                    ", expireDate='" + expireDate + '\'' +
                    '}';
        }
    }
}
