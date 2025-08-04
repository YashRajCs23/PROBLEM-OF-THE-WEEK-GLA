public class searchInSortedList {
    // Brute-force approach: O(N)
    public static boolean searchBrute(int[] arr, int x) {
        for (int val : arr) {
            if (val == x) return true;
        }
        return false;
    }

    // Optimized Binary Search: O(log N)
    public static boolean searchOptimized(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == x) return true;
            else if (arr[mid] < x) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }


    // Optimized binary search without *, /, <<
    public static boolean search(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = safeMid(low, high);
            if (arr[mid] == x) return true;
            else if (arr[mid] < x) low = mid + 1;
            else high = mid - 1;
        }
        return false;
    }
    // Compute (low + high) / 2 without /, *, or >>
    private static int safeMid(int low, int high) {
        int sum = low + high;
        int mid = 0;
        while (sum >= 2) {
            sum -= 2;
            mid++;
        }
        return mid;
    }
    public static void main(String[] args) {
        int[] sortedList = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;

        // Test brute-force search
        boolean foundBrute = searchBrute(sortedList, target);
        System.out.println("Brute-force search found " + target + ": " + foundBrute);

        // Test optimized binary search
        boolean foundOptimized = searchOptimized(sortedList, target);
        System.out.println("Optimized binary search found " + target + ": " + foundOptimized);

        // Test optimized search without *, /, <<
        boolean foundSafe = search(sortedList, target);
        System.out.println("Safe binary search found " + target + ": " + foundSafe);
    }
}