import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.ToString;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Function;

public class ThreadsExample {

    @Test
    void oneByOne() {
        String description = downloadDescription();
        String photos = downloadPhotos();
        BigDecimal price = downloadPrice();
        long additionalInfo = downloadAdditionalInfo();

        String descrFinished = transform(descriptionFunction, description);
        String photosFinished = transform(photosFunction, photos);
        String priceFinished = transform(priceFunction, price);
        String addInfoFinished = transform(additionalInfoFunction, additionalInfo);
        ProductForTest productForTest = new ProductForTest(descrFinished, photosFinished, priceFinished, addInfoFinished);
        System.out.println(productForTest);
    }

    private class MyRunnable implements Runnable { // to samo co ()->
        @Override
        public void run() {
            transform(descriptionFunction, downloadDescription());
        }
    }

    @Test
    void threads() {

        List<Thread> threads = createThreads();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    void executors() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        MyRunnable descrRunnable = new MyRunnable();
        Runnable photosrunnable = () -> transform(photosFunction, downloadPhotos());
        Runnable priceRunnable = () -> transform(priceFunction, downloadPrice());
        Runnable addInfoRunnable = () -> transform(additionalInfoFunction, downloadAdditionalInfo());

        executorService.execute(descrRunnable);
        executorService.execute(photosrunnable);
        executorService.execute(priceRunnable);
        executorService.execute(addInfoRunnable);

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }

    @Test
    void future() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Future<String> descrFuture = executorService.submit(() -> downloadDescription());
        Future<String> photosFuture = executorService.submit(() -> downloadPhotos());
        Future<BigDecimal> priceFuture = executorService.submit(() -> downloadPrice());
        Future<Long> addInfoFuture = executorService.submit(() -> downloadAdditionalInfo());


        executorService.submit(() -> transform(descriptionFunction, descrFuture.get()));
        executorService.submit(() -> transform(photosFunction, photosFuture.get()));
        executorService.submit(() -> transform(priceFunction, priceFuture.get()));
        executorService.submit(() -> transform(additionalInfoFunction, addInfoFuture.get()));

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }

    @Test
    void completableFuture() {
        CompletableFuture<String> descrCF = CompletableFuture.supplyAsync(() -> downloadDescription());
        CompletableFuture<String> descr2CF = CompletableFuture.supplyAsync(() -> downloadDescription2());
        CompletableFuture<String> photosCF = CompletableFuture.supplyAsync(() -> downloadPhotos());
        CompletableFuture<BigDecimal> priceCF = CompletableFuture.supplyAsync(() -> downloadPrice());
        CompletableFuture<Long> addInfoCF = CompletableFuture.supplyAsync(() -> downloadAdditionalInfo());

        CompletableFuture<String> descrResultCF =
                descrCF.applyToEitherAsync(descr2CF, e -> {
                    System.out.println("ściągniął się " + e);
                    return e;
                }).thenApplyAsync(descriptionFunction);
        CompletableFuture<String> photosResultCF = photosCF.thenApplyAsync(photosFunction);
        CompletableFuture<String> priceResultCF = priceCF.thenApplyAsync(priceFunction);
        CompletableFuture<String> additionalInfoResultCF = addInfoCF.thenApplyAsync(additionalInfoFunction);

        descrResultCF.join();
        photosResultCF.join();
        priceResultCF.join();
        additionalInfoResultCF.join();

    }

    private List<Thread> createThreads() {
        Thread descrThread = new Thread(new MyRunnable());
        Thread photosThread = new Thread(() -> transform(photosFunction, downloadPhotos()));
        Thread priceThread = new Thread(() -> transform(priceFunction, downloadPrice()));
        Thread additionalInfoThread = new Thread(() -> transform(additionalInfoFunction, downloadAdditionalInfo()));

        return Lists.newArrayList(descrThread, photosThread, priceThread, additionalInfoThread);
    }

    private Function<BigDecimal, String> priceFunction = price -> price.toEngineeringString();
    private Function<String, String> descriptionFunction = description -> description + "!";
    private Function<String, String> photosFunction = photos -> photos + "?";
    private Function<Long, String> additionalInfoFunction = addInfo -> Long.toString(addInfo);


    private <T> String transform(Function<T, String> function, T value) {
        simulateDelay(4500);
        System.out.println(Thread.currentThread().getName() + " Transformation finished ");
        return function.apply(value);
    }


    private String downloadDescription() {
        System.out.println(Thread.currentThread().getName() + " Descr downloading...");
        simulateDelay(4000);
        System.out.println(Thread.currentThread().getName() + " Descr downloaded...");
        return "OPIS";
    }

    private String downloadDescription2() {
        System.out.println(Thread.currentThread().getName() + " Descr 2 downloading...");
        simulateDelay(RandomUtils.nextInt(2500, 5500));
        System.out.println(Thread.currentThread().getName() + " Descr 2 downloaded...");
        return "INNY OPIS";
    }

    private BigDecimal downloadPrice() {
        System.out.println(Thread.currentThread().getName() + " Price downloading...");
        simulateDelay(2500);
        System.out.println(Thread.currentThread().getName() + " Price downloaded...");
        return BigDecimal.valueOf(2.3);
    }

    private String downloadPhotos() {
        System.out.println(Thread.currentThread().getName() + " Photos downloading...");
        simulateDelay(3000);
        System.out.println(Thread.currentThread().getName() + " Photos downloaded...");
        return "Zdjęcia";
    }

    private long downloadAdditionalInfo() {
        System.out.println(Thread.currentThread().getName() + " Add info downloading...");
        simulateDelay(3500);
        System.out.println(Thread.currentThread().getName() + " Add info downloaded...");
        return 123L;
    }

    private void simulateDelay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @ToString
    @AllArgsConstructor
    private class ProductForTest {
        private String description;
        private String price;
        private String photos;
        private String additionalInfo;
    }
}
