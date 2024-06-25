package demo_exam.v2_305.n1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        List<Person> personList = new ArrayList<>();
        List<Events> eventList = new ArrayList<>();
        Map<String, Integer> stat = new HashMap<>();

        try (BufferedReader brp = new BufferedReader(new InputStreamReader(new FileInputStream("history_person"), StandardCharsets.UTF_8));
             BufferedReader bre = new BufferedReader(new InputStreamReader(new FileInputStream("history_events") , StandardCharsets.UTF_8))) {
            String line;

            while ((line = brp.readLine()) != null) {
                String[] data = line.split(",");
                personList.add(new Person(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
                stat.put(data[1], stat.containsKey(data[1]) ? stat.get(data[1]) + 1 : 1);
            }

            while ((line = bre.readLine()) != null) {
                String[] data = line.split(",");
                eventList.add(new Events(data[0], data[1], Integer.parseInt(data[2]), Integer.parseInt(data[3])));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String country = "расия";

        List<Person> personInCountry = personList.stream().filter(x -> x.getStrana().equals(country)).toList();

        Map<String, Integer> names = personInCountry.stream().collect(Collectors.toMap(person -> person.getName(), person -> (person.getBirth()+person.getDead())/2));
        long result = eventList.stream().filter(event -> names.containsKey(event.getPersonName())
                && names.get(event.getPersonName())<= event.getStars()).count();
    }
}
