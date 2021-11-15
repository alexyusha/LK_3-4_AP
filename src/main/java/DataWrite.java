import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        Map<String, Double> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<String, Double> entry : list){
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }

    public static void writeStatisticsJson() throws IOException {
        String path = "resources\\Statistics";
        String filenameAllSum = "allSum.json";
        String filenameAllIntruder = "allIntruder.json";

        if (!(Files.exists(Paths.get(path)))){
            Files.createDirectories(Paths.get(path));
        }

        File file = new File(path + "\\" + filenameAllSum);
        File file1 = new File(path + "\\" + filenameAllIntruder);
        new ObjectMapper().writeValue(file, sortMap());
        new ObjectMapper().writeValue(file1, IntruderStat.intruderStatSet);
    }
}
