package zzzTest.excelLoader.models.auditRule;

import zzzTest.excelLoader.excelReader.ExcelColumnTitle;

public class ProtectBrand {
    @ExcelColumnTitle("品牌")
    public String sensitive_word;

    public int word_type=1;

    public String getSensitive_word() {
        return sensitive_word;
    }

    public void setSensitive_word(String sensitive_word) {
        this.sensitive_word = sensitive_word;
    }

    public int getWord_type() {
        return word_type;
    }

    public void setWord_type(int word_type) {
        this.word_type = word_type;
    }
}