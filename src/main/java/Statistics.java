import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class Statistics{

    public static Set<File> searchFileJson(String root, TypeFile typeFile){
        Set<File> pathsSet = new HashSet<>();
        Queue<File> queue = new ArrayDeque<>();
        queue.add(new File(root));

        while (!queue.isEmpty()){
            File[] files = new File(String.valueOf(queue.poll())).listFiles();
            for (File files1 : files){
                if (files1.isDirectory()){
                    queue.add(files1.getAbsoluteFile());
                }
                else{
                    if (files1.getName().endsWith(typeFile.getValue())){
                        pathsSet.add(files1.getAbsoluteFile());
                    }
                }
            }
        }
        return pathsSet;
    }


    public static synchronized void allSumViolation(File file) throws IOException {
        List<Intruder> list = new ObjectMapper().readValue(file, new TypeReference<>() {});
        for (Intruder intruder : list){
            String key = intruder.getTypeViolation().toString();
            double allCost = DataWrite.statistics.get(key);
            double cost = intruder.getCostFine();
            DataWrite.statistics.put(key, allCost + cost);
        }

    }

    public static void allListViolation(File file) throws IOException {
        List<Intruder> list = new ObjectMapper().readValue(file, new TypeReference<>(){});
        for (Intruder intruder : list){
            if (IntruderStat.intruderStatSet.isEmpty()){
                new IntruderStat(intruder.getIdIntruder(), 1, intruder.getCostFine(), intruder.getCostFine());
            }else{
                boolean flag = false;
                for (IntruderStat intruderStat : IntruderStat.intruderStatSet){
                    if (intruder.getIdIntruder() == intruderStat.getIdIntruder()){
                        int count = intruderStat.getCountViolation()+1;
                        double allCost = intruderStat.getAllViolationSum() + intruder.getCostFine();
                        intruderStat.setCountViolation(count);
                        intruderStat.setAllViolationSum(allCost);
                        intruderStat.setAvgSum(allCost/count);
                        flag = true;
                        break;
                    }
                }
                if (!flag){
                    new IntruderStat(intruder.getIdIntruder(), 1, intruder.getCostFine(), intruder.getCostFine());
                }
            }
        }
    }
}
