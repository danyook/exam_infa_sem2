package demo_exam.v1_305.n2;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>(List.of(50001, 12, 150, 23, 20, 120, 1233, 1234));

        System.out.println(list);
        Collections.sort(list, new MyRazdadComparator());
        System.out.println(list);
    }
}

