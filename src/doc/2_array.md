## 数组

1）数组是一种线性表结构，使用一组连续的内存空间来存储一组具有相同类型的数据。

2）除了数组，链表、栈、队列也是线性表结构。而树、堆、图是非线性表（数据间不是简单的前后关系）。

### 数组随机访问元素时的寻址公式
1）a[i]_address = base_address + i * data_type_size
2）base_address指首地址，即下标为0的元素的地址；data_type_size指元素类型的大小，例如int为4字节。
3）数组下标从0开始，一方面是继承了C等早期语言的习惯，另一方面可以减少寻址时的减1操作，减少CPU消耗。

### 数组操作的时间复杂度
1）随机访问：O(1)时间复杂度
由于数组采用内存连续存储的方式以及类型固定，可以使用寻址公式，直接定位元素在内存的位置。
2）插入和删除:O(n)时间复杂度
为了保证数组存储空间的连续性，进行插入和删除操作一般会伴随数据的移动。
对于插入和删除操作，最好情况是在数组末尾进行就不需要移动数据，时间复杂度为O(1);
最坏的情况是在数组的开头进行，所有数据都需要移动一位，时间复杂度为O(n);
在数组的每个位置插入或删除元素的概率一样，平均时间复杂度为O(n)。
3）提升效率的方法：
如果不要求数组中元素严格有序，那在插入时可以不移动数据，把对应位置的原数据复制到数组尾部，
在对应位置用新数据覆盖掉原数据，这样就是O(1)时间复杂度；
如果不追求内存空间连续性，那在删除时把对应位置记录下来，在数组没有空间存储数据时再触发删除操作，
把标记位置一次删除（或直接在标记位置分配空间），大大提升了效率。