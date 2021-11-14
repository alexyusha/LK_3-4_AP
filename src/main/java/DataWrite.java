import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataWrite {
    public  static Map<String, Double> statistics = new HashMap<>();

    static {
        for (TypeViolation type : TypeViolation.values()){
            statistics.put(type.toString(), 0.0);
        }
    }

    public static Map<String, Double> sortMap(){
        List<Map.Entry<String, Double>> list = new LinkedList<>(statistics.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> a, Map.Entry<String, Double> b) {
                return b.getValue().compareTo(a.getValue());
            }
        });

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void writeStatisticsJson() throws IOException {
        String allSum = "resources\\Statistics\\allSum.json";
        String allIntruder = "resources\\Statistics\\allIntruder.json";
        File file = new File(allSum);
        File file1 = new File(allIntruder);
        new ObjectMapper().writeValue(file, sortMap());
        new ObjectMapper().writeValue(file1, IntruderStat.intruderStatSet);
    }
}
