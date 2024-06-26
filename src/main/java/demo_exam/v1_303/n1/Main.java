package demo_exam.v1_303.n1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> data = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("class1"))) {
            while (bufferedReader.ready()) {
                data.add(bufferedReader.readLine().replace('{', ' ').replace('}', ' '));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Class class1 = new Class();
        String aPackage = data.stream().filter(o -> o.startsWith("package")).findAny().get();
        int ind = 0;
        for (int i = 0; i < data.size(); i++){
            if (data.get(i).contains("class")){
                ind = i;
                String[] cur = data.get(i).split(" ");
                for (int j = 0; j < cur.length; j++){
                    if (cur[j].equals("class")){
                        class1.setName(cur[j + 1]);
                        break;
                    }

                }
                break;
            }
        }
        class1.setPackageClass(aPackage.replace(';', ' ').trim().split(" ")[1]);

        List<String> modificators = new ArrayList<>(List.of("private", "protected", "public", "static"));
        List<String> neccesaryMethods = data.stream().filter(s -> s.contains("(") && !s.contains("new") && !s.contains("()")).toList();
        List<Variable> variables = new ArrayList<>();
        for (String line : neccesaryMethods) {
            String[] arrayOfParameters = line.substring(line.indexOf("(") + 1, line.indexOf(")")).replace("; ", ", ").split(", ");
            for (String str : arrayOfParameters) {
                String[] oneParameter = str.split(" ");
                if (oneParameter.length == 4 && oneParameter[2].equals("=") || oneParameter.length == 2){
                    variables.add(new Variable(oneParameter[0], oneParameter[1], "methodParameter", class1.getName()));
                }

            }
        }
        data = data.subList(ind + 1, data.size()).stream().map(String::trim).toList();
        List<String> var1 = data.stream()
                .filter(s -> s.endsWith(";")
                        && !s.contains("this.")
                        && !(s.startsWith("throw") || s.startsWith("new") || s.startsWith("return"))
                        && s.split(" ").length != 1
                        && !s.split(" ")[0].contains("(")
                        && !s.split(" ")[1].equals("=")).toList();
        for (String s : var1) {
            String[] strings = s.replace(", ", " ").split(" ");
            Variable variable = new Variable();
            for (int i = 0; i < strings.length;i++){
                if (strings[i].equals("=") ){
                    break;
                }
                if (modificators.contains(strings[i])){
                    continue;
                }
                else {
                    if (variable.getTypeVar() == null){
                        variable.setTypeVar(strings[i]);
                    }
                    else if (variable.getName() == null){
                        if (strings[i].contains("/") || strings[i].contains("+") || strings[i].contains("-") || strings[i].contains("%") ) break;
                        variable.setName(strings[i].replace(";", ""));
                        variable.setVarClass(class1.getName());
                        variable.setTypeCreate("Local");
                        variables.add(variable);
                    }
                    else {
                        variables.add(new Variable(variable.getTypeVar(), strings[i], variable.getTypeCreate(), variable.getVarClass()));
                    }
                }
            }


        }
        for (Variable variable: variables){
            System.out.println(variable);
        }
    }
}




