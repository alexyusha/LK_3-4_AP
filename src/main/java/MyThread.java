import lombok.SneakyThrows;

import java.io.File;
import java.util.concurrent.CountDownLatch;

public class MyThread implements Runnable {
    private CountDownLatch latch;
    private File file;
    private String filename;

    MyThread(CountDownLatch c, File f) {
        this.latch = c;
        this.file = f;
        filename = f.getName();
        new Thread(this);
    }

    @SneakyThrows
    public void run() {
        Statistics.allSumViolation(file);
        Statistics.allListViolation(file);
        System.out.println(filename);
        latch.countDown();
        Thread.sleep((long) (Math.random() * 1500));
        System.out.println(filename + " completed");
    }
}
