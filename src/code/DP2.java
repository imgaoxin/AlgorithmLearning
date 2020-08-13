package code;

public class DP2 {
    /*
     * 有一个数字序列包含n个不同的数字，如何求出这个序列中的最长递增子序列长度？ 如：[2, 9, 3, 6, 5, 1, 7] => [2, 3, 5, 7]
     * => 4
     */

    public static void main(String[] args) {
        int[] arr = new int[] { 2, 9, 3, 6, 5, 1, 7 };
        int len = arr.length;

        int maxLen = dpCore(arr, len);
        System.out.println(maxLen);

        int maxLen2 = dpCore2(arr, len);
        System.out.println(maxLen2);

    }

    private static int dpCore2(int[] arr, int len) {
        // 索引标记子序列长度，值记录当前索引长度的子序列的最小结尾元素值
        int[] process = new int[len + 1];
        for (int i = 0; i < process.length; i++) {
            process[i] = -1;
        }
        process[1] = arr[0];

        // 记录标记过的最大长度
        int r = 0;

        // process数组的索引，代表了子序列长度
        int p;
        for (int i = 1; i < len; i++) {
            p = 1;
            // i + 1为当前计算到了第几个元素，也就是当前可能符合要求的最大长度
            while (p <= i + 1) {
                // 记录标记过的最大长度
                if (p > r) {
                    r = p;
                }

                // 记录同长度子序列的最小结尾元素值
                if (process[p] == -1 || arr[i] < process[p]) {
                    process[p] = arr[i];
                    break;
                }

                // 升序，大于等于的情况还可以向后加长度
                p++;
            }

        }

        return r;
    }

    private static int dpCore(int[] arr, int len) {
        int[][] result = new int[len][len];

        // 第一个位置，最长递增序列长度为为1
        result[0][0] = 1;
        // 向下复制结果；
        result[1][0] = 1;

        for (int i = 1; i < len; i++) {
            // arr[0 ... i]的最长递增序列长度是“arr[0 ... 前一个值比其小的位置]的最长递增序列长度 + 1”
            for (int j = 0; j < i; j++) {
                if (arr[i] >= arr[j]) {
                    int tmp = result[i][j] + 1;
                    if (tmp > result[i][i]) {
                        result[i][i] = tmp;
                    }
                }
                // 向下复制结果；
                if (i < len - 1) {
                    result[i + 1][j] = result[i][j];
                }
            }
            // 向下复制结果；
            if (i < len - 1) {
                result[i + 1][i] = result[i][i];
            }
        }

        return result[len - 1][len - 1];
    }
}
