package A_ReadingNotes.Java8_in_Action.Part2;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private Trader trader;
    private int year;
    private int value;
}
