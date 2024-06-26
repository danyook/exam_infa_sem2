package demo_exam.v2_303.n1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Method {
    private String name;
    private String modifier;
    private String staticOrNo;
    private int paramCount;
    private String voidOrNo;
    private String className;
}
