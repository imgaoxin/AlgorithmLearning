## 时间和空间复杂度

1）数据结构与算法解决的是计算机处理数据（执行代码）时提升执行效率和降低占用空间的问题，
复杂度分析从时间和空间两个维度评估数据结构和算法的性能。

2）复杂度分析与性能测试相比，是一种事前统计（预估）的方法，不依赖于执行环境和数据规模。

3）大O表示法：T(n) = O(f(n))，T(n)表示执行总时间，f(n)表示代码的执行次数，O代表执行
时间与执行次数的正向关系，表示了执行时间随数据规模增长的变化趋势。也叫作渐进时间复杂度（asymptotic time complexity）。

4）空间复杂度，全称是渐进空间复杂度（asymptotic space complexity），表示算法的存储空
间与数据规模之间的增长关系。

### Big O notation 常见复杂度表示
O(1):  Constant Complexity 常数复杂度
O(log n):  Logarithmic Complexity 对数复杂度
O(n):  Linear Complexity 线性复杂度
O(nlogn):  Linear Logarithmic Complexity 线性对数复杂度
O(n^2): N square Complexity 平⽅
O(n^3): N square Complexity ⽴⽅
O(2^n): Exponential Growth 指数
O(n!): Factorial 阶乘
注意：只看最高量级复杂度，忽略系数

### 例子
二叉树遍历-前序、中序、后序：O(N)
图的遍历：O(N)
搜索算法：DFS、BFS - O(N)
二分查找：O(logN)
归并排序：O(NlogN)

### 拓展
* 最好，最坏，平均，均摊时间复杂度
* https://zh.wikipedia.org/wiki/%E4%B8%BB%E5%AE%9A%E7%90%86
* https://www.zhihu.com/question/21387264
