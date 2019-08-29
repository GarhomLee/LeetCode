https://leetcode.com/problems/kth-largest-element-in-a-stream/

// Intuition：Insertion Sort

// 优化思路：PriorityQueue
//         维护size为k的minHeap，意味着当前有k-1个元素比minHeap顶部元素大，所以顶部元素就是第k大元素。
// 时间复杂度：add(): O(log k)
// 空间复杂度：O(k)

class KthLargest {
    PriorityQueue<Integer> pq;
    int k;
    
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<>();
        for (int n: nums) {
            pq.offer(n);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }
    
    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}