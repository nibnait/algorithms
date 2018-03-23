package zzzTest.JsoupTest;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class BJ_Jsoup {

    private static final String CORP_NAME = "名称";
    private static final String LEGAL_PERSON = "法定代表人";
    private static final String LEGAL_PERSON2 = "经营者姓名";
    private static final String REGIST_NUMBER = "统一社会信用代码";
    private static final String REGIST_NUMBER2 = "注册号";
    private static final String REGIST_ADDRESS = "住所";
    private static final String REGIST_ADDRESS2 = "经营场所";
    private static final String EXPIRE_DATE = "营业期限至";

    public static void main(String[] args) {
        try {
            String BJurl3 = "http://qyxy.baic.gov.cn/wap/wap/creditWapAction!qr.dhtml?id=ff8080815457447b015475e61a6d54b9";
            String BJurl2 = "http://qyxy.baic.gov.cn/wap/wap/creditWapAction!qr.dhtml?id=ff80808154cd96280154e6eb93512f38";
            String BJurl4 = "http://qyxy.baic.gov.cn/wap/wap/creditWapAction!qr.dhtml?id=a1a1a1a031b22b240131b6c5904107fd";
            String BJurl = "http://qyxy.baic.gov.cn/wap/wap/creditWapAction!qr.dhtml?id=1D59AB12B1D14CC08CE9E8AFEC253048";
            Document document = Jsoup.connect(BJurl).get();
            //获取网页源码文本内容
//            System.out.println(document.toString());
            //获取指定class的内容指定tag的元素
            Elements dl = document.getElementsByTag("dl");
            Element element = dl.get(0);
            Elements dt = element.getElementsByTag("dt");
            Elements dd = element.getElementsByTag("dd");
            License licenseInfo = new License();
            int cursor = 0;
            for (int i = 0; i < dt.size(); i++) {
                if (dt.get(i).text().equals(REGIST_NUMBER)
                        || dt.get(i).text().equals(REGIST_NUMBER2)) {
                    if (dd.get(i+1).text().contains("注")) {
                        cursor++;
                        licenseInfo.setLicenseNo(getLicenseNo(dd.get(i+cursor).text()));
                    } else {
                        licenseInfo.setLicenseNo(dd.get(i).text());
                    }
                    continue;
                }
                if (dt.get(i).text().equals(CORP_NAME)) {
                    licenseInfo.setCorpName(dd.get(i+cursor).text());
                    continue;
                }
                if (dt.get(i).text().equals(LEGAL_PERSON)
                        || dt.get(i).text().equals(LEGAL_PERSON2)) {
                    licenseInfo.setLegalPerson(dd.get(i+cursor).text());
                    continue;
                }
                if (dt.get(i).text().equals(REGIST_ADDRESS)
                        || dt.get(i).text().equals(REGIST_ADDRESS2)) {
                    licenseInfo.setAddress(dd.get(i+cursor).text());
                    continue;
                }
                if (dt.get(i).text().equals(EXPIRE_DATE)) {
                    licenseInfo.setExpireDate(dd.get(i+cursor).text());
                    continue;
                }
            }

            System.out.println(licenseInfo.toString());

        } catch (IOException e) {
            System.out.println("解析出错！");
            e.printStackTrace();
        }

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

    static class License {
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
                    ", licenseNo='" + licenseNo + '\'' +
                    ", address='" + address + '\'' +
                    ", expireDate='" + expireDate + '\'' +
                    '}';
        }
    }
}
