package code;

public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 8, 9, 13, 66, 999 };
        testSimpleBinarySearch(arr, 6, 2);

        int[] arr2 = new int[] { 2, 8, 9, 13, 13, 13, 66, 66, 999 };
        testFirstEqBinarySearch(arr2, 9, 13);
        testLastEqBinarySearch(arr2, 9, 13);
        testFirstGteBinarySearch(arr2, 9, 12);
        testLastLteBinarySearch(arr2, 9, 998);

    }

    private static void testLastLteBinarySearch(int[] arr, int n, int value) {
        int index = lastLteBinarySearch(arr, n, value);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }

    /*
     * 查找小于等于给定值的最后一个元素
     */
    private static int lastLteBinarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        if (high >= 0 && arr[high] <= value) {
            return high;
        }
        return -1;
    }

    private static void testFirstGteBinarySearch(int[] arr, int n, int value) {
        int index = firstGteBinarySearch(arr, n, value);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }

    /*
     * 查找大于等于给定值的第一个元素
     */
    private static int firstGteBinarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            if (arr[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        if (low < n && arr[low] >= value) {
            return low;
        }
        return -1;
    }

    private static void testLastEqBinarySearch(int[] arr, int n, int value) {
        int index = lastEqBinarySearch(arr, n, value);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }

    /*
     * 查找等于给定值的最后一个元素
     */
    private static int lastEqBinarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // 找到等于给定值的最后一个元素，意味着 arr[mid] == value 时还要继续向后找
            if (arr[mid] <= value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        // 考虑找到最后一个 arr[mid] == value 时，high 不做处理，low后移了一位
        if (high >= 0 && arr[high] == value) {
            return high;
        }
        return -1;
    }
    // private static int lastEqBinarySearch(int[] arr, int low, int high, int
    // value) {
    // int mid = 0;
    // int index = -1;
    // while (low <= high) {
    // mid = low + ((high - low) >> 1);
    // if (arr[mid] == value) {
    // index = mid;
    // break;
    // } else if (arr[mid] > value) {
    // high = mid - 1;
    // } else {
    // low = mid + 1;
    // }
    // }
    // if (index != -1) {
    // while (index <= arr.length && arr[index] == value) {
    // index++;
    // }
    // index--;
    // }
    // return index;
    // }

    private static void testFirstEqBinarySearch(int[] arr, int n, int value) {
        int index = firstEqBinarySearch(arr, n, value);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }

    /*
     * 查找等于给定值的第一个元素
     */
    private static int firstEqBinarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        while (low <= high) {
            mid = low + ((high - low) >> 1);
            // 因为有序数组中查找等于给定值的第一个元素，
            // 所以 arr[mid] == value 时，high也要前移，比较前面有没有相同值
            if (arr[mid] >= value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        // 考虑找到第一个 arr[mid] == value 时，low 不处理，high 前移了一位
        if (low < n && arr[low] == value) {
            return low;
        }
        return -1;
    }

    private static void testSimpleBinarySearch(int[] arr, int n, int value) {
        int index = simpleBinarySearch(arr, n, value);
        System.out.println("index: " + index + ", value: " + arr[index]);
    }

    /*
     * 不考虑数组中有重复元素的情况
     */
    private static int simpleBinarySearch(int[] arr, int n, int value) {
        int low = 0;
        int high = n - 1;
        int mid = 0;
        // 注意不要遗漏 mid = low = high 的情况
        while (low <= high) {
            // 避免数据值上溢使用差值，提高效率使用位运算
            mid = low + ((high - low) >> 1);
            if (arr[mid] == value) {
                return mid;
            } else if (arr[mid] < value) {
                // 如果写成 low = mid 可能死循环
                low = mid + 1;
            } else {
                // 如果写成 high = mid 可能死循环
                high = mid - 1;
            }
        }
        return -1;
    }
}