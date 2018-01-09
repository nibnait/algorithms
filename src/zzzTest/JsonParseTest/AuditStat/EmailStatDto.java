package zzzTest.JsonParseTest.AuditStat;

public class EmailStatDto {

    private Integer operationId;
    private String operatorName;
    private String  operationDate;
    private Integer auditNumber;
    private Integer passNumber;
    private Integer rollbackNumber;

    public Integer getOperationId() {
        return operationId;
    }

    public void setOperationId(Integer operationId) {
        this.operationId = operationId;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(String operationDate) {
        this.operationDate = operationDate;
    }

    public Integer getAuditNumber() {
        return auditNumber;
    }

    public void setAuditNumber(Integer auditNumber) {
        this.auditNumber = auditNumber;
    }

    public Integer getPassNumber() {
        return passNumber;
    }

    public void setPassNumber(Integer passNumber) {
        this.passNumber = passNumber;
    }

    public Integer getRollbackNumber() {
        return rollbackNumber;
    }

    public void setRollbackNumber(Integer rollbackNumber) {
        this.rollbackNumber = rollbackNumber;
    }
}
