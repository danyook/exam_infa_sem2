package demo_exam.v1_305.n2;

import java.util.Comparator;

public class MyRazdadComparator implements Comparator<Integer> {

    @Override
    public int compare(Integer n1, Integer n2) {
        int length1 = getLength(n1);
        int length2 = getLength(n2);
        int maxLen = Math.max(length1, length2);

        for (int i = 0; i < maxLen; i++) {
            int digit1 = getDigit(n1, length1, i);
            int digit2 = getDigit(n2, length2, i);

            if (digit1 < digit2) {
                return -1;
            }
            if (digit1 > digit2) {
                return 1;
            }
        }
        return 0;
    }

    private int getLength(int num) {
        if (num == 0) return 1;
        int length = 0;
        while (num != 0) {
            length++;
            num /= 10;
        }
        return length;
    }

    private int getDigit(int num, int length, int pos) {
        int lenDiff = length - pos - 1;
        if (lenDiff < 0) {
            return -1;
        }
        while (lenDiff > 0) {
            num /= 10;
            lenDiff--;
        }
        return num % 10;
    }
}
