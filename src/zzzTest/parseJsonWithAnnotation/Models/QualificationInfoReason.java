package zzzTest.parseJsonWithAnnotation.Models;

import lombok.Data;

import java.util.Map;

@Data
public class QualificationInfoReason {
    //法定代表人
    private Map<String, String> legalReason;

    //主体资质
    private Map<String, String> licenseReason;

    //行业资质
    private Map<String, String> permitReason;

    //资质信息其他
    private String qualificationInfoOther;

    public QualificationInfoReason() {
    }

    public QualificationInfoReason(String qualificationInfoOther) {
        this.qualificationInfoOther = qualificationInfoOther;
    }
}
