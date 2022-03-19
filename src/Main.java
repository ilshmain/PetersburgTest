import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        // Так как у человека всего 48 часов и он должен поспать дважды по 8 часов,
        // то в итоге ему доступно 16 часов в сутки, 32 за двое суток.
        double count = 16;
        double allTimeUse = 0;
        List<Plans> plans = new ArrayList<>();
        List<Plans> reality = new ArrayList<>();

        try (BufferedReader  reader = new BufferedReader(new FileReader("text.txt"))) {
            String line;
            String[] array;
            while ((line = reader.readLine()) != null) {
                String regex = "\\.*\\s{2,}";
                array = line.split(regex);
                plans.add(new Plans(Integer.parseInt(array[2]), Double.parseDouble(array[1]), array[0]));
            }
            Collections.sort(plans);
        }

        for (int i = 0; i < 2; i++) {
            double time = 0;
            for (Plans plans1 : plans) {
                if (!reality.contains(plans1) && (time + plans1.getTime() <= count)) {
                    allTimeUse += plans1.getTime();
                    reality.add(plans1);
                    time += plans1.getTime();
                }
            }
        }
        for (Plans real : reality) {
            System.out.println("Человек сможет посетить: " + real.getPlace() + ", важность: " + real.getImportance() +
                    ", затраченное время: " + real.getTime());
        }
        System.out.println("Всего затрачено времени: " + allTimeUse);
    }
}
