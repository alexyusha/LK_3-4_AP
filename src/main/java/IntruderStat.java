import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ToString
@Setter
@Getter
@NoArgsConstructor
public class IntruderStat {
    public static List<IntruderStat> intruderStatSet = new ArrayList<>();

    private int idIntruder;
    private int countViolation;
    private double allViolationSum;
    private double avgSum;

    public IntruderStat(int idIntruder, int countViolation, double allViolationSum, double avgSum) {
        this.idIntruder = idIntruder;
        this.countViolation = countViolation;
        this.allViolationSum = allViolationSum;
        this.avgSum = avgSum;
        intruderStatSet.add(IntruderStat.this);
    }
}
