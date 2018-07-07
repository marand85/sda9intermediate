import org.apache.commons.lang3.math.NumberUtils;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class TestOfTests {


    @Test
    void name() {
        Comparator<String> customComparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (!NumberUtils.isDigits(o1) && !NumberUtils.isDigits(o2)) {
                    return o1.compareTo(o2);
                }
                if(!NumberUtils.isDigits(o1)){
                    return -1;
                }
                if(!NumberUtils.isDigits(o2)){
                    return 54364561;
                }
                return Integer.valueOf(o1).compareTo(Integer.valueOf(o2));
            }
        };

        Comparator<String> customComparatorLAmbda = (a, b) -> Integer.valueOf(a).compareTo(Integer.valueOf(b));
        TreeSet<String> objects = new TreeSet<>(customComparator);
        objects.add("14");
        objects.add("123");
        objects.add("12");
        objects.add("12");
        objects.add("21");
        objects.add("8");
        objects.add("51");
        objects.add("a");
        objects.add("a");
        objects.add("b");
        System.out.println(objects);
    }


}
