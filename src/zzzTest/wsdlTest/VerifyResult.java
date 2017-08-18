package zzzTest.wsdlTest;

public class VerifyResult {

    public static final Integer valid = 1;
    public static final Integer invalid = 2;
    public static final Integer blank = 3;


    /**
     * 主体资质（营业执照）校验结果码
     * 1：有效（绿色）
     * 2：无效（红色）
     * 3: -- null
     */
    private Integer licenseVerifyCode;

    /**
     * 主体资质校验结果信息
     */
    private String licenseVerifyMessage;

    /**
     * 行业资质（餐饮服务许可证）校验结果码
     * 1：有效（绿色）
     * 2：无效（红色）
     * 3: -- null
     */
    private Integer permitVerifyCode;

    /**
     * 行业资质校验结果信息
     */
    private String permitVerifyMessage;

    public VerifyResult(Integer licenseVerifyCode, String licenseVerifyMessage, Integer permitVerifyCode, String permitVerifyMessage) {
        this.licenseVerifyCode = licenseVerifyCode;
        this.licenseVerifyMessage = licenseVerifyMessage;
        this.permitVerifyCode = permitVerifyCode;
        this.permitVerifyMessage = permitVerifyMessage;
    }

    public Integer getLicenseVerifyCode() {
        return licenseVerifyCode;
    }

    public void setLicenseVerifyCode(Integer licenseVerifyCode) {
        this.licenseVerifyCode = licenseVerifyCode;
    }

    public String getLicenseVerifyMessage() {
        return licenseVerifyMessage;
    }

    public void setLicenseVerifyMessage(String licenseVerifyMessage) {
        this.licenseVerifyMessage = licenseVerifyMessage;
    }

    public Integer getPermitVerifyCode() {
        return permitVerifyCode;
    }

    public void setPermitVerifyCode(Integer permitVerifyCode) {
        this.permitVerifyCode = permitVerifyCode;
    }

    public String getPermitVerifyMessage() {
        return permitVerifyMessage;
    }

    public void setPermitVerifyMessage(String permitVerifyMessage) {
        this.permitVerifyMessage = permitVerifyMessage;
    }
}

