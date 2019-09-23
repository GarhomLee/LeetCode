https://leetcode.com/problems/moving-average-from-data-stream/

// 思路：Queue
//         维护最大长度为cap的Queue，同时维护全局变量sum。对于新加进Queue的元素，更新sum，求average。
//         如果Queue达到了最大长度cap，那么需要将头部的最早加入的元素poll出来，同时将其从sum中减去。
// 时间复杂度：O(1)
// 空间复杂度：O(cap)
// 犯错点：1.细节错误：将Queue的头部元素删除后，还要将其从sum中减去。

class MovingAverage {
    Queue<Integer> queue;
    int capacity;
    double sum;
    
    /** Initialize your data structure here. */
    public MovingAverage(int cap) {
        capacity = cap;
        queue = new LinkedList<>();
        sum = 0;
    }
    
    public double next(int val) {
        if (capacity == 0) {
            return 0;
        }
        
        queue.offer(val);
        sum += val;
        double average = sum / queue.size();
        if (queue.size() == capacity) {
            // queue.poll();  // {Mistake 1}
            sum -= queue.poll();  // {Correction 1}
        }
        
        return average;
    }
}


优化：用长度为cap的数组模拟Queue