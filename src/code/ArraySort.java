package code;

public class ArraySort {
    public static void main(String[] args) {
        // 待排序数组
        int[] arr = new int[] { 1, 2, 99, 23, 5, 123, 67, 4, 9, 9, 10, 24, 66, 44, 22, 88 };
        // 待排序数组元素个数
        int n = 16;

        bubbleSort(arr, n);
        printArray(arr);

        insertionSort(arr, n);
        printArray(arr);

        shellSort(arr, n);
        printArray(arr);

        selectionSort(arr, n);
        printArray(arr);

        mergeSort(arr, n);
        printArray(arr);

        quickSort(arr, n);
        printArray(arr);

        int k = 5;
        int result = searchBigK(arr, n, k);
        System.out.println("第" + k + "大元素：" + result);

    }

      /*
     * O(n)时间复杂度查找第K大元素
     */
    private static int searchBigK(int[] arr, int n, int k) {
        if (k > n) {
            throw new RuntimeException("k must little than or equals n.");
        }

        int h = 0, t = n - 1;
        int p = partition(arr, h, t);
        // 查找第K小元素
        // while (p + 1 != k) {
        // if (p + 1 < k) {
        // h = p + 1;
        // } else {
        // t = p - 1;
        // }
        // p = partition(arr, h, t);
        // }
        while (n - p != k) {
            if (n - p < k) {
                t = p - 1;
            } else {
                h = p + 1;
            }
            p = partition(arr, h, t);
        }
        return arr[p];
    }
    
    /*
     * 快速排序
     */
    private static void quickSort(int[] arr, int n) {
        quickSortCore(arr, 0, n - 1);
    }

    private static void quickSortCore(int[] arr, int i, int j) {
        // 终止条件
        if (i >= j) {
            return;
        }

        // 递推公式
        // int p = partition(arr, i, j);
        int p = partition2(arr, i, j);
        quickSortCore(arr, i, p - 1);
        quickSortCore(arr, p + 1, j);
    }

    /*
     * 获取分区点 取任一值为分区点（这里取最后一个），小于分区点的放在左侧，大于分区点的放在右侧，最后返回分区点索引。
     */
    private static int partition(int[] arr, int head, int tail) {
        // 将大的元素放在右侧，小的元素换到左侧，分区点位置作为中介存储，交换过程中变换分区点的位置逐步接近预期位置。
        int p = arr[tail];
        while (head < tail) {
            while (head < tail && arr[head] < p) {
                head++;
            }
            arr[tail] = arr[head];// 不能tail--,tail = head的情况，指针移动会造成错误
            while (head < tail && arr[tail] >= p) {
                tail--;
            }
            arr[head] = arr[tail];// head++,tail = head的情况，指针移动会造成错误
        }
        arr[tail] = p;
        return tail;
    }

    private static int partition2(int[] arr, int head, int tail) {
        // 遍历将小于分区点的元素向左侧排列，最后将分区点与左侧已处理区域后的第一个元素交换
        int pivot = arr[tail];
        int s = head;
        for (; head < tail; head++) {
            if (arr[head] < pivot) {
                arr[head] = arr[head] + arr[s] - (arr[s] = arr[head]);
                s++;
            }
        }
        arr[tail] = arr[s];
        arr[s] = pivot;
        return s;
    }

    /*
     * 归并排序
     */
    private static void mergeSort(int[] arr, int n) {
        mergeSortCore(arr, 0, n - 1);
    }

    private static void mergeSortCore(int[] arr, int i, int j) {
        // 终止条件
        if (i >= j) {
            return;
        }

        // 递推公式
        int mid = i + ((j - i) >> 1);
        mergeSortCore(arr, i, mid);
        mergeSortCore(arr, mid + 1, j);
        merge(arr, i, mid, j);
    }

    private static void merge(int[] arr, int i, int mid, int j) {
        int t = i;
        int k = mid + 1;
        // 临时排序数组
        int[] tmp = new int[j - i + 1];

        int p = 0;
        while (i <= mid && k <= j) {
            if (arr[i] <= arr[k]) {
                tmp[p++] = arr[i++];
            } else {
                tmp[p++] = arr[k++];
            }
        }

        // 将可能剩余未merge元素拷贝到临时排序数组
        int start = i, end = mid;
        if (k <= j) {
            start = k;
            end = j;
        }
        while (start <= end) {
            tmp[p++] = arr[start++];
        }

        // 临时排序数组拷贝到原数组
        p = 0;
        while (t <= j) {
            arr[t++] = tmp[p++];
        }
    }

    /*
     * 选择排序
     */
    private static void selectionSort(int[] arr, int n) {
        for (int i = 0; i < n; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            // if (minIndex == i) {
            // continue;
            // }
            // 数据交换，每轮最小值交换到数组前
            arr[i] = arr[i] + arr[minIndex] - (arr[minIndex] = arr[i]);
        }
    }

    /*
     * 希尔排序：对插入排序的优化，就是动态调整元素比较的步长，最后步长为1时实际就是插入排序
     */
    private static void shellSort(int[] arr, int n) {
        int step = n;
        // 动态调整步长，最后step=1时就是进行了一次标准的插入排序，可以保证每个位置元素都排好序
        // 减少数据移动到预期（排序）位置的步数，提高效率。
        // 实际上是分组插入排序
        // 思路是逐步提高数组元素的有序度，开始步长大，元素需要移动的次数（距离）小，最后一次进行插入排序因为有序度高，效率会很高
        // 时间复杂度：最好情况O(nlogn) 空间复杂度O(1)
        // 是不稳定的（相等元素可能会改变位置）
        while ((step /= 2) > 0) {
            // 想象成按step切分成行，形成表，列数为step
            for (int col = 0; col < step; col++) {
                // 对每列进行插入排序
                for (int i = col + step; i < n; i += step) {
                    int value = arr[i];
                    int j = i - step;
                    while (j >= 0 && arr[j] > value) {
                        arr[j + step] = arr[j];
                        j -= step;
                    }
                    arr[j + step] = value;
                }
            }
        }
    }

    /*
     * 插入排序
     */
    private static void insertionSort(int[] arr, int n) {
        // 左侧为已排序区间（开始时以第一个元素为已排序区间），右侧为未排序区间。
        // 未排序区间第一个元素依次与已排序区间元素倒序比较，依次移位。
        for (int i = 1; i < n; i++) {
            int value = arr[i];
            int j = i - 1;
            // for (; j >= 0; j--) {
            // if (arr[j] > value) {
            // arr[j + 1] = arr[j];
            // } else {
            // break;
            // }
            // }
            while (j >= 0 && arr[j] > value) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = value;
        }
    }

    /*
     * 冒泡排序
     */
    private static void bubbleSort(int[] arr, int n) {
        // 优化：如果不再发生元素交换，说明已经排好序，可以提前终止。
        for (int i = 0; i < n; i++) {
            boolean flag = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // arr[j] = arr[j] + arr[j + 1];
                    // arr[j + 1] = arr[j] - arr[j + 1];
                    // arr[j] = arr[j] - arr[j + 1];
                    // 算数运算符结合律->从左至右
                    arr[j] = arr[j] + arr[j + 1] - (arr[j + 1] = arr[j]);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }

    /**
     * 输出数组数据
     */
    private static void printArray(int[] arr) {
        StringBuilder s = new StringBuilder("[ ");
        for (int i : arr) {
            s.append(i);
            s.append(" ");
        }
        s.append("]");
        System.out.println(s.toString());
    }
}