import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.util.Calendar;

@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Intruder {
    private int idIntruder;
    private String firstName;
    private String lastName;
    @JsonDeserialize(using = CalendarDeserializer.class)
    private Calendar dateViolation;
    private TypeViolation typeViolation;
    private float costFine;

}


