import lombok.AllArgsConstructor;
import lombok.ToString;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.function.Function;

public class ThreadsExample {

    @Test
    void oneByOne() {
        String description = downloadDescription();
        String photos = downloadPhotos();
        BigDecimal price = downloadPrice();
        long additionalInfo = downloadAdditionalInfo();

        transform()
//        ProductForTest productForTest = new ProductForTest();
    }

    private Function<BigDecimal, String> priceFunction = price -> price.toEngineeringString();
    private Function<String, String> descriptionFunction = description -> description + "!";


    private <T> String transform(Function<T, String> function) {
        return "";
    }


    private String downloadDescription() {
        System.out.println(Thread.currentThread().getName() + " Descr downloading...");
        simulateDelay(4000);
        System.out.println(Thread.currentThread().getName() + " Descr downloaded...");
        return "OPIS";
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
