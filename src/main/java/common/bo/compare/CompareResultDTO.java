package common.bo.compare;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by nibnait on 2021/06/23
 */
@Data
@NoArgsConstructor
public class CompareResultDTO {

    private boolean isMatch;

    private List<FieldComparison> modifiedFields;

    private List<FieldComparison> missingFields;

    private List<FieldComparison> newFields;

    private String message;

    @Data
    public static class FieldComparison {
        private String field;

        private Object expected;

        private Object actual;
    }

    public CompareResultDTO(boolean isMatch) {
        this.isMatch = isMatch;
    }
}
