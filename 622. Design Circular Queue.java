https://leetcode.com/problems/design-circular-queue/

// 思路：Design + Array with Pointers
//         维护以下变量：
//         1）int[] queue，存放所有的元素
//         2）int head，tail，分别指向queue的头部元素位置和【尾部元素位置的下一位（即新元素可放置的位置）】。
//         3）int capacity，表示queue能存放的最大容量。
//         4）int size，表示queue当前存放的元素个数。
//         实现以下功能：
//         1）boolean enQueue(int value)，将value放入queue中，如果queue未满，操作成功返回true；否则，queue已满，
//             不能再加新元素，操作失败返回false。
//             将value放在tail位置，同时更新tail和size。
//         2）boolean deQueue()，从queue删除头部元素，如果queue不为空，操作成功返回true；否则，queue为空，没有
//             可删除元素，操作失败返回false。
//             移动head指针到下一位，同时更新size。
//         3）int Front()，当queue不为空时返回head位置的元素，否则返回-1.
//         4）int Rear()，当queue不为空时返回【tail-1位置】的元素，否则返回-1.
//         5）boolean isEmpty()，判断size是否为0
//         6）boolean isFull()，判断size是否为capacity
// 时间复杂度：all functions: O(1)
// 空间复杂度：O(k)

class MyCircularQueue {
    int[] queue;
    int head;
    int tail;
    int size;
    int capacity;
    
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        queue = new int[k];
        head = 0;
        tail = 0;
        size = 0;
        capacity = k;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        
        queue[tail] = value;
        tail = (tail + 1) % capacity;
        size++;
        return true;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        
        head = (head + 1) % capacity;
        size--;
        return true;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return queue[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return queue[(tail - 1 + capacity) % capacity];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == capacity;
    }
}