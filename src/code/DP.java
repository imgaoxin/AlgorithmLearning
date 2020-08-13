package code;

public class DP {
    /**
     * 背包问题
     */

    // 物品及重量
    static int[] arr = new int[] { 2, 2, 4, 6, 3 };
    // 背包容量
    static int w = 9;
    // 最优解存储
    static int r = 0;
    
    public static void main(String[] args) {
        
        int cw = 0;
        int ci = 0;
        
        opt1(ci, cw);
        System.out.println(r);

        r = 0;
        boolean[][] bool = new boolean[arr.length][w + 1];
        opt2(bool, ci, cw);
        System.out.println(r);

        r = 0;
        opt3(arr.length, cw);
        System.out.println(r);

        r = 0;
        int[][] tmp = new int[arr.length][w + 1];
        opt4(tmp);
        for (int is : tmp[arr.length - 1]) {
            if (is > r)  r = is;   
        }
        System.out.println(r);

        r = 0;
        int[] tmp2 = new int[w + 1];
        opt5(tmp2);
        for (int is : tmp2) {
            if (is > r)  r = is;   
        }
        System.out.println(r);

    }

    // 动态规划 空间复杂度优化
    private static void opt5(int[] tmp) {
        tmp[0] = 0;
        if(arr[0] <= w)
            tmp[arr[0]] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            // 从大到小遍历判断，避免从小到大的数据覆盖和误判
            for (int j = w - arr[i]; j >= 0; j--) {
                if (tmp[j] == j) {
                    tmp[j + arr[i]] = j + arr[i]; 
                }      
            } 
        }
    }
    // 动态规划 尾递归优化
    private static void opt4(int[][] tmp) {
        if(arr[0] <= w)
            tmp[0][arr[0]] = arr[0];

        for (int i = 1; i < tmp.length; i++) {
            for (int j = 0; j < tmp[0].length; j++) {
                if (tmp[i-1][j] + arr[i] <= w) {
                    // 放
                    tmp[i][tmp[i-1][j] + arr[i]] = tmp[i-1][j] + arr[i]; 
                } 
                if (tmp[i-1][j] > tmp[i][j]) {
                    // 不放
                    tmp[i][j] = tmp[i-1][j]; 
                }        
            } 
        }
    }

    // 动态规划
    private static void opt3(int ci, int cw) {
        if(ci == 0 || cw == w){
            r = cw > r ? cw : r; 
            return;
        }

        opt3(ci - 1, cw);

        if(cw + arr[ci-1] <= w){
            opt3(ci - 1, cw + arr[ci-1]);
        }
    }

      // 回溯法 存储优化
    private static void opt2(boolean[][] bool, int ci, int cw){
        if(ci == arr.length || cw == w){
            r = cw > r ? cw : r; 
            return;
        }
        
        if(bool[ci][cw]){
            return;
        }
        bool[ci][cw] = true;

        opt2(bool, ci + 1, cw);

        if(cw + arr[ci] <= w){
            opt2(bool, ci + 1, cw + arr[ci]);
        }
    }

    // 回溯法
    private static void opt1(int ci, int cw){
        if(ci == arr.length || cw == w){
            r = cw > r ? cw : r; 
            return;
        }

        opt1(ci + 1, cw);

        if(cw + arr[ci] <= w){
            opt1(ci + 1, cw + arr[ci]);
        }
    }

}