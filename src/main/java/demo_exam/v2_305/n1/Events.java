package demo_exam.v2_305.n1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Events {
    private String name;
    private String personName;
    private int stars;
    private int end;
}
