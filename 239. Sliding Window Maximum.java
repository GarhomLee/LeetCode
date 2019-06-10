https://leetcode.com/problems/sliding-window-maximum/

// 解法一：Monotonic Queue
//         自建一个class MonotonicQueue，内部实现为Deque，维护以下性质：
//             1）FIFO，所以尾进头出
//             2）单调递减（即单调非增），头部元素为Queue的最大元素，尾部元素为Queue的最小元素
//         同时，MonotonicQueue实现以下功能：
//             1）push(int val)，将val加入Queue，且维持Queue单调递减的性质
//             2）poll()，移除头部元素（即最大元素）
//             3）peek()，观察头部元素（即最大元素）
//         有了MonotonicQueue后，开始遍历nums数组。sliding window为nums[start:end]。
//         当end < k-1，只需要push元素，相当于初始化MonotonicQueue；当end >= k-1，即start >= 0时，开始求最大元素。
//         如果遇到了nums[start] == mq.peek()的情况，表示最大元素在window边缘，在window下一次移位就会来到window外，所以
//         需要将最大元素poll出来。否则，window边缘的nums[start]不是最大元素，意味着在之前的push阶段已经被poll掉了，所以不需额外操作。
// 时间复杂度：O(n)，每个元素最多被push和poll各一次
// 空间复杂度：O(n)
// 犯错点：1.要判断是否要poll元素，即nums[start]是否等于mq.peek()，需要在start >= 0即window已经形成的情况下。

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (k == 0) return new int[0];
        
        int[] res = new int[nums.length - k + 1];
        MonotonicQueue mq = new MonotonicQueue();
        for (int end = 0; end < nums.length; end++) {
            mq.push(nums[end]);
            int start = end - k + 1;
            /* window with k elements has been constructed */
            if (start >= 0) {
                res[start] = mq.peek();  // fill in res array with max element stored in MonotonicQueue
                if (nums[start] == mq.peek()) mq.poll();  // {Correction 1}
            }
            //if (nums[start] == mq.peek()) mq.poll();  // {Mistake 1: start is valid only if start >= 0}
        }
        
        return res;
    }
    
    /** monotonic decreasing queue */
    class MonotonicQueue {
        Deque<Integer> deque;
        
        public MonotonicQueue() {
            deque = new ArrayDeque<Integer>();
        }
        
        /* push val into the tail of the queue until the previous element is greater than or equal to val */
        public void push(int val) {
            while (!deque.isEmpty() && deque.peekLast() < val) {
                deque.pollLast();
            }
            deque.addLast(val);
        }
        
        /* poll the head element of the queue, which is the max element in the queue */
        public Integer poll() {
            return deque.poll();
        }
        
        /* retrieve the head element of the queue without removing it, which is the max element in the queue */
        public Integer peek() {
            return deque.peek();
        }
    }
}

// 解法二：maxHeap，在每个window取maxHeap.top，和Brute force差别不大
// 时间复杂度：O(n*k)
// 空间复杂度：O(k)
// 犯错点：1.边界条件k==0

class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        /* corner case */
        if (k == 0) return new int[0];  // {Mistake 1} {Correction 1}
        
        int[] res = new int[nums.length - k + 1];

        /* initialize the first window */
        PriorityQueue<Integer> pq = new PriorityQueue(Collections.reverseOrder());
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        res[0] = pq.peek();

        /* sliding window */
        for (int i = 1; i < nums.length - k + 1; i++) {  // the termination condition of for loop is corresponding to the size of res array
            pq.remove(nums[i - 1]);  // time complexity of remove(Object o) is O(k)
            pq.offer(nums[i + k - 1]);
            res[i] = pq.peek();
        }
        return res;
    }
}