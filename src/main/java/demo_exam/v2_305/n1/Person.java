package demo_exam.v2_305.n1;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class Person {
    private String name;
    private String strana;
    private int birth;
    private int dead;
}
