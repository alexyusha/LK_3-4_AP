import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutorService {
    MyExecutorService() throws IOException {
        String root = "resources\\All Violation";
        Set<File> files = Statistics.searchFileJson(root, TypeFile.JSON);
        CountDownLatch cdl1 = new CountDownLatch(files.size());
        ExecutorService executor = Executors.newFixedThreadPool(10);

        System.out.println("Запуск потоков");
        for (File file : files){
            executor.execute(new MyThread(cdl1, file));
        }
        try {
            cdl1.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executor.shutdown();
        DataWrite.writeStatisticsJson();
        System.out.println("Завершение потоков");
    }
}
