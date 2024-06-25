package demo_exam.v1_302.n1;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Element {
    private int coef;
    private int degX;
    private int degY;

    public Element() {}

    @Override
    public String toString() {
        return "{" +
                "coef=" + coef +
                ", degX=" + degX +
                ", degY=" + degY +
                '}';
    }
}
