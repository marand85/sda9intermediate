import net.bytebuddy.implementation.bind.annotation.Super;
import org.junit.jupiter.api.Test;

public class LambdasExample {

    @FunctionalInterface
    public interface SuperChecker {
        boolean check(Integer value);
    }

    private class SuperCheckerImpl implements SuperChecker{
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

        boolean resultFromAnonymousClassImmediately= new SuperChecker() {
            @Override
            public boolean check(Integer value) {
                return value % 2 == 0;
            }
        }.check(3);

        SuperChecker superFunctionalStyleChecker = (a) -> a % 2==0;
        boolean resultFromLambda = superFunctionalStyleChecker.check(3);
    }
}
