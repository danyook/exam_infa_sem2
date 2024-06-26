package demo_exam.v2_303.n1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<String> MODIFICATORS = new ArrayList<>(List.of("private", "protected", "public"));
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        List<Class> classList = new ArrayList<>();
        List<Method> methodList = new ArrayList<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("class1"))) {
            while (bufferedReader.ready()) {
                data.add(bufferedReader.readLine().replace("{", " ").replace("}", " ").trim());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Class class1 = new Class();

        String package1 = data.stream().filter(o -> o.startsWith("package")).findAny().get();
        class1.setClassPackage(package1.replace(";","").trim().split(" ")[1]);

        for (int i = 0; i < data.size(); i++) {
            if (data.get(i).contains("class")) {
                String[] curr = data.get(i).split(" ");
                for (int j = 0; j < curr.length; j++) {
                    if (curr[j].equals("class")) {
                        class1.setName(curr[j + 1]);
                        break;
                    }
                }
                break;
            }
        }

        classList.add(class1);


        List<String> methodsTop = data.stream().filter(o -> o.length() != 0)
                                                .filter(o -> MODIFICATORS.contains(o.split(" ")[0]))
                                                .filter(o -> !o.contains(";"))
                                                .filter(o -> !o.contains("class")).toList();



        for (int i = 0; i < methodsTop.size(); i++) {
            Method method = new Method();

            String curr = methodsTop.get(i);

            String[] param = curr.replace(")","").split("\\(");
            if (param.length == 1) {
                method.setParamCount(0);
            } else {
                method.setParamCount(param[1].trim().split(",").length);
            }
            String[] info = curr.split("\\(")[0].split(" ");

            method.setClassName(classList.get(0).getName());

            for (int j = 0; j < info.length; j++) {
                method.setModifier(info[0]);

                if (info[1].equals("static")) {
                    method.setStaticOrNo("static");
                    method.setVoidOrNo(info[2]);
                    method.setName(info[3]);
                } else {
                    method.setStaticOrNo("not static");
                    method.setVoidOrNo(info[1]);
                    method.setName(info[2]);
                }
            }
            methodList.add(method);
        }

        System.out.println(classList);
        System.out.println(methodList);

        int allFileCountMethod = 0;
        int allFileCountMethodMore2param = 0;
        for (int i = 0; i < classList.size(); i++) {
            String className = classList.get(i).getName();

            long countMethods = methodList.stream().filter(o -> o.getClassName().equals(className)).count();
            long countStaticM = methodList.stream().filter(o -> o.getStaticOrNo().equals("static")).count();
            long countPublicM = methodList.stream().filter(o -> o.getModifier().equals("public")).count();
            long countVoidM = methodList.stream().filter(o -> o.getVoidOrNo().equals("void")).count();
            long sumParam = 0;

            sumParam += methodList.stream().mapToLong(o -> o.getParamCount()).sum();
            double averageParam = 1.0 * sumParam / countMethods;

            System.out.println(countMethods + " " + countStaticM + " " + countPublicM + " " + countVoidM + " " + averageParam);

            List<Method> listMore2param = methodList.stream().filter(o -> o.getParamCount() < 2).toList();

            allFileCountMethodMore2param += listMore2param.size();
            allFileCountMethod += countMethods;


        }

        System.out.println(allFileCountMethod / 2 < allFileCountMethodMore2param);


    }
}
