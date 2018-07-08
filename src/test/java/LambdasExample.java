import bookstore.categories.dtos.AdminCategoryDTO;
import bookstore.categories.entities.Category;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class LambdasExample {

    @FunctionalInterface
    public interface SuperChecker {
        boolean check(Integer value);
    }

    private class SuperCheckerImpl implements SuperChecker {
        @Override
        public boolean check(Integer value) {
            return value % 2 == 0;
        }
    }

    @Test
    void anonymousClass() {
        SuperCheckerImpl superChecker = new SuperCheckerImpl();
        boolean resultFromSuperCheckerImpl = superChecker.check(3);

        SuperChecker checker = new SuperChecker() {
            @Override
            public boolean check(Integer value) {
                return value % 2 == 0;
            }
        };

        boolean resultFromAnonymousClass = checker.check(3);

        boolean resultFromAnonymousClassImmediately = new SuperChecker() {
            @Override
            public boolean check(Integer value) {
                return value % 2 == 0;
            }
        }.check(3);

        SuperChecker superFunctionalStyleChecker = (a) -> a % 2 == 0;
        boolean resultFromLambda = superFunctionalStyleChecker.check(3);
    }

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);

        default String a() {
            return "hahaha";
        }
    }

    @Test
    void mathOperations() {
        // 2 + 2 = 4
        // 2 - 2 = 0
        // 3 / 1 = 3

        MathOperation adding = (x, y) -> x + y;
        int addingResult = adding.operation(2, 2); //1
        operateOnMathData(2, 2, adding);//2 - z zastosowaniem osobnej metody

        Assert.assertEquals(4, addingResult);

        MathOperation subtracting = (x, y) -> x - y;
        int subtractResult = subtracting.operation(2, 2); //1
        operateOnMathData(2, 2, (x, y) -> x - y);//2 - z zastosowaniem osobnej metody


        operateOnMathData(2, 2, (x, y) -> x / y);
        operateOnMathData(2, 2, (x, y) -> x % y);

        Integer integer = new Integer(2);
        Integer integer2 = new Integer(3);
        int i = integer.compareTo(integer2);
        System.out.println(i);

    }

    private int operateOnMathData(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


    @FunctionalInterface
    public interface MyComparator<T> {
        int customCompare(T obj1, T obj2); //todo (przyjmuje dwa parametry tej samej dowolnej klasy
        // i zwraca jeden odpowiedni dla sortowania
    }

    @Test
    void compareTest() {
        Category category1 = new Category(1, "1223");
        Category category2 = new Category(3, "223");
        List<Category> categories = Lists.newArrayList(category1, category2);

        AdminCategoryDTO adminCategoryDTO1 =
                AdminCategoryDTO.builder().id("1234").text("Kat 1").build();
        AdminCategoryDTO adminCategoryDTO2 =
                AdminCategoryDTO.builder().id("234").text("Kat 2").build();
        List<AdminCategoryDTO> categoryDTOS = Lists.newArrayList(adminCategoryDTO1, adminCategoryDTO2);

        MyComparator compareByIntegerId = new MyComparator<Category>() {
            @Override
            public int customCompare(Category obj1, Category obj2) {
                return obj1.getId().compareTo(obj2.getId());
            }
        };
        int customCompareByIntegerId = compareByIntegerId.customCompare(category1, category2);
        Assertions.assertEquals(-1, customCompareByIntegerId);

        MyComparator<Category> compareByNameUsingString = new MyComparator<Category>() { // to można zamienić na lambde
            @Override
            public int customCompare(Category obj1, Category obj2) {
                return obj1.getName().compareTo(obj2.getName());
            }
        };
        int customCompareByStringName = compareByNameUsingString.customCompare(category1, category2);
        Assertions.assertEquals(-1, customCompareByStringName);

        MyComparator<Category> compareByNameUsingNumberValues =
                (cat1, cat2) -> Integer.valueOf(cat1.getName()).compareTo(Integer.valueOf(cat2.getName()));
        int customCompareByStringNumberValue = compareByNameUsingNumberValues.customCompare(category1, category2);

        Assertions.assertEquals(1, customCompareByStringNumberValue);


        //pierwsze sortowanie po id
        Collections.sort(categories, (a, b) -> a.getId().compareTo(b.getId()));
        Assertions.assertTrue(categories.get(0).getId().equals(1));
        //drugie sortowanie po name (liczbowo)
        Collections.sort(categories, (a, b) -> Integer.valueOf(a.getName()).compareTo(Integer.valueOf(b.getName())));
        Assertions.assertTrue(categories.get(0).getId().equals(3));
        //trzecie sortowanie po name (stringowo)
        Collections.sort(categories, (a, b) -> a.getName().compareTo(b.getName()));
        Assertions.assertTrue(categories.get(0).getId().equals(1));

    }

    @FunctionalInterface
    interface MyBiComparator<T, U> {
        int biCompare(T obj1, U obj2);
    }

    @Test
    void compareTwoTypes() {
        Integer x = 20;
        String s = "21";

        MyBiComparator<Integer, String> myBiComparatorUsingNumericValue =
                (a, b) -> a.compareTo(Integer.valueOf(b));
        int biCompare = myBiComparatorUsingNumericValue.biCompare(x, s);
        Assertions.assertTrue(-1 == biCompare);

        MyBiComparator<Integer, String> myBiComparatorUsingNumericValueReversed =
                (a, b) -> Integer.valueOf(b).compareTo(a);
        int biCompareREversed = myBiComparatorUsingNumericValueReversed.biCompare(x, s);
        Assertions.assertTrue(1 == biCompareREversed);


    }
}
