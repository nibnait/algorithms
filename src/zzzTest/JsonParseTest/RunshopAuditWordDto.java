package zzzTest.JsonParseTest;

public class RunshopAuditWordDto {
    private long id;
    private String sensitiveWord;
    private short wordType;
    private short isValid;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSensitiveWord() {
        return sensitiveWord;
    }

    public void setSensitiveWord(String sensitiveWord) {
        this.sensitiveWord = sensitiveWord;
    }

    public short getWordType() {
        return wordType;
    }

    public void setWordType(short wordType) {
        this.wordType = wordType;
    }

    public short getIsValid() {
        return isValid;
    }

    public void setIsValid(short isValid) {
        this.isValid = isValid;
    }
}
