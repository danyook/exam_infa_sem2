package demo_exam.v1_305.n1;

public class MatrixElem {
    private int i;
    private int j;
    private int value;

    public MatrixElem(int i, int j, int value) {
        this.i = i;
        this.j = j;
        this.value = value;
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value + " ";
    }
}