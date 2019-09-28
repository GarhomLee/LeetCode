https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/

// 对比：209. Minimum Size Subarray Sum，本题允许负数存在。

// 思路：Monotonic Queue (Deque)。参考：https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution
//         关键点：维护递增的prefix sum的Deque，使得搜索sum >= k的最小subarray长度可以通过不断取出
//             Deque的头部元素实现。【Deque中实际存放的是index】。
//         step0: 预处理，得到prefix sum的数组sum。
//         step1: 遍历所有sum[i]，在满足sum[i] - sum[deque.getFirst()] >= k的情况下，不断通过
//             【移除Deque头部元素】，缩小subarray范围，同时更新minLen。
//         step2: 然后不断【移除Deque尾部元素】，维护递增的Monotonic Queue性质。
//         step3: 将当前元素的index加入Deque尾部。
//         step3: 遍历完所有sum[i]，根据minLen是否从初始值被更新为其他值，决定返回值为-1还是minLen。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int shortestSubarray(int[] A, int K) {
        int minLen = Integer.MAX_VALUE;
        int[] sum = new int[A.length + 1];
        for (int i = 1; i <= A.length; i++) {
            sum[i] = sum[i - 1] + A[i - 1];
        }
        
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < sum.length; i++) {
            while (!deque.isEmpty() && sum[i] - sum[deque.getFirst()] >= K) {
                minLen = Math.min(minLen, i - deque.removeFirst());
            }
            while (!deque.isEmpty() && sum[i] < sum[deque.getLast()]) {
                deque.removeLast();
            }
            
            deque.addLast(i);
        }
        
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}