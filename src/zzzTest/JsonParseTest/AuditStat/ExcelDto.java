package zzzTest.JsonParseTest.AuditStat;

import java.util.List;

public class ExcelDto {
    private String name;
    private List<Excel_Date_AuditNumber> auditNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Excel_Date_AuditNumber> getAuditNumber() {
        return auditNumber;
    }

    public void setAuditNumber(List<Excel_Date_AuditNumber> auditNumber) {
        this.auditNumber = auditNumber;
    }
}
