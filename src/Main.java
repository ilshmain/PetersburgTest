import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // Так как у человека всего 48 часов и он должен поспать дважды по 8 часов,
        // то в итоге ему доступно 16 часов в сутки, 32 за двое суток.

        // В этот раз, для получения максиальной важности, будем считать, что человек не должен
        // находиться все указанное время в каком-то месте, а может побыть там лишь часть от этого времени в один день,
        // а еще часть в другой

        List<Plans> plans = new ArrayList<>();
        Map<Double, String> mapOneDay = new LinkedHashMap<>();
        Map<Double, String> maptwoDay = new LinkedHashMap<>();

        try (BufferedReader  reader = new BufferedReader(new FileReader("text.txt"))) {
            String line;
            String[] array;
            while ((line = reader.readLine()) != null) {
                String regex = "\\.*\\s{2,}";
                array = line.split(regex);
                plans.add(new Plans(Double.parseDouble(array[2]), Double.parseDouble(array[1]), array[0]));
            }
        }
        plans.sort(Comparator.comparingDouble(Plans::valueOneWeight).reversed());
        double allImportance = 0;
        final double allTime = 16;
        double currentTime; // текущий вес
        double currentImportance = 0;;// текущая ценность
        int currentIndex = 0;
        int days = 0;
        double peremen = 0;

        while (days < 2) {
            currentTime = 0;
            currentImportance = 0;
            while (currentIndex < plans.size() && currentTime != allTime) {
                if (currentTime + plans.get(currentIndex).getTime() < allTime) {
                    peremen = plans.get(currentIndex).getImportance();
                    currentImportance += peremen;
                    currentTime += plans.get(currentIndex).getTime();
                } else {
                    peremen = ((allTime - currentTime)/plans.get(currentIndex).getTime()) *
                            plans.get(currentIndex).getImportance();
                    currentImportance += peremen;
                    if (days == 0) {
                        mapOneDay.put(peremen, plans.get(currentIndex).getPlace());
                        allImportance += currentImportance;
                        currentImportance = (plans.get(currentIndex).getTime() -(allTime - currentTime))/
                                plans.get(currentIndex).getTime() *
                                plans.get(currentIndex).getImportance();
                        maptwoDay.put(currentImportance, plans.get(currentIndex).getPlace());
                        currentIndex++;
                        break;
                    }
                    currentTime = allTime;
                }
                if (days == 0) {
                    mapOneDay.put(peremen, plans.get(currentIndex).getPlace());
                } else {
                    maptwoDay.put(peremen, plans.get(currentIndex).getPlace());
                }
                currentIndex++;
            }
            allImportance += currentImportance;
            days++;
        }


        System.out.println("Человек посетит в певый день:");
        for (Map.Entry<Double, String> m : mapOneDay.entrySet()) {
            System.out.println(m.getValue() +", важность: " + m.getKey());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Человек посетит во второй день:");
        for (Map.Entry<Double, String> m : maptwoDay.entrySet()) {
            System.out.println(m.getValue() +", важность: " + m.getKey());
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Общая важность: " + allImportance);
    }

}
