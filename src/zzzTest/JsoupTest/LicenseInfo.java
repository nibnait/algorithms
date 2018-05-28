package zzzTest.JsoupTest;

import lombok.Data;

@Data
public class LicenseInfo {
    /**
     * 注册号
     */
    private String licenseNo;

    /**
     * 单位名称
     */
    private String corpName;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 注册地址
     */
    private String address;

    /**
     * 有效期
     */
    private String expireDate;

    /**
     * 识别结果是否成功
     */
    private boolean recognitionResult;

    /**
     * 该省份是否支持扫一扫识别
     */
    private boolean isSupport = false;

    public static LicenseInfo newLicenseInfoBySupport(boolean isSupport) {
        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.isSupport = isSupport;
        return licenseInfo;
    }


    public static LicenseInfo newLicenseInfoByResult(boolean recognitionResult) {
        LicenseInfo licenseInfo = new LicenseInfo();
        licenseInfo.recognitionResult = recognitionResult;
        return licenseInfo;
    }
}
