package demo_exam.v1_305.n1;


import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[][] matrix = {{0, 0, 3},
                {0, 0, 6},
                {7, 8, 9}};
        MatrixCode matrixCode = new MatrixCode(matrix);
        matrixCode.insert(1, 1, 10);
        matrixCode.delete(1, 2);
        System.out.println(Arrays.deepToString(matrixCode.decode()));
        System.out.println(matrixCode.sum());

        List<Integer> nums = new ArrayList<>();
        nums.add(5);
        nums.add(1);
        nums.add(117);
        nums.add(9);
        nums.add(35);
        nums.add(44);

        nums.sort(new IntComparator());
        nums.forEach(System.out::println);
    }

    public static class IntComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {

            int power1 = (int) Math.pow(10, getLength(o1) - 1) ;
            int power2 = (int) Math.pow(10, getLength(o2) - 1) ;

            while (power1 != 0 && power2 != 0) {
                int digit1 = o1 / power1;
                int digit2 = o2 / power2;
                if (digit1 < digit2) {
                    return -1;
                } else if (digit1 > digit2) {
                    return 1;
                }

                o1 %= power1;
                o2 %= power2;
                power1 /= 10;
                power2 /= 10;
            }
            if (power1 > power2) {
                return 1;
            } else if (power1 < power2) {
                return -1;
            }
            return 0;
        }

        private static  int getLength(Integer num){
            int count = 1;
            num /= 10;
            while (num != 0){
                count += 1;
                num /= 10;
            }
            return count;
        }
    }
}