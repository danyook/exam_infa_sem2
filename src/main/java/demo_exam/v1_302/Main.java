package demo_exam.v1_302;

public class Main {
    public static void main(String[] args) {
        Polinom polinom = new Polinom("polinom_filename");
        System.out.println(polinom.toString());
        polinom.derivate(0);
        System.out.println(polinom.toString());
    }
}
