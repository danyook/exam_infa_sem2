package demo_exam.v2_305.n2;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("numbers_v2_305_n2"))) {
        String line;
        while ((line = br.readLine()) != null) {
            nums.add(Integer.parseInt(line));
        }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < 3; i++) {
            int myi = i;
            Thread runnable = new Thread(() -> {
                try (FileOutputStream file = new FileOutputStream("test" + myi + ".bin")) {
                    for (Integer num : nums) {
                        file.write(num);
                    }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            runnable.start();
        }
    }
}
