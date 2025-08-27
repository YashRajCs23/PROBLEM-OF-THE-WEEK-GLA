import java.util.function.Function;
import java.util.stream.IntStream;
public class AddSubtractAll {

    // ---------------------------------------------------
    // 1. Class with apply() chaining (Closest to curried style)
    // ---------------------------------------------------
    static class AddSubtract {
        private int total;
        private int index;

        public AddSubtract(int first) {
            this.total = first;
            this.index = 1;
        }

        /**
         * TC: O(1) per apply call
         * SC: O(1)
         */
        public AddSubtract apply(int next) {
            if (index % 2 == 1) total += next; // odd -> add
            else total -= next;                // even -> subtract
            index++;
            return this;
        }

        public int getResult() {
            return total;
        }

        @Override
        public String toString() {
            return String.valueOf(total);
        }

        // Factory method (to avoid writing 'new')
        public static AddSubtract start(int first) {
            return new AddSubtract(first);
        }
    }

    // ---------------------------------------------------
    // 2. Lambda-based "functional style"
    // ---------------------------------------------------
    static class AddSubtractLambda {
        /**
         * TC: O(1) per apply call
         * SC: O(1)
         */
        public static Function<Integer, AddSubtract> start(int first) {
            return (next) -> new AddSubtract(first).apply(next);
        }
    }

    // ---------------------------------------------------
    // 3. Varargs utility method
    // ---------------------------------------------------
    /**
     * TC: O(n) → iterate once over nums
     * SC: O(1)
     */
    public static int addSubtractVarargs(int... nums) {
        int total = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (i % 2 == 1) total += nums[i];
            else total -= nums[i];
        }
        return total;
    }

    // ---------------------------------------------------
    // 4. Stream-based approach
    // ---------------------------------------------------
    /**
     * TC: O(n) → single pass over nums using IntStream
     * SC: O(1) → aside from stream overhead
     */
    public static int addSubtractStream(int... nums) {
        return IntStream.range(0, nums.length)
                .map(i -> (i % 2 == 0 ? nums[i] : -nums[i]))
                .sum();
    }
}