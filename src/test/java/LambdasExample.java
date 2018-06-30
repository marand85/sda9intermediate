import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

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
        default String a(){
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

    }

    private int operateOnMathData(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }


}
