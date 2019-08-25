https://leetcode.com/contest/biweekly-contest-7/problems/minimum-cost-to-connect-sticks/

// 思路：minHeap
//         根据题意，需要取当前所拥有的元素中的最小的两个，求和后放回去，然后再进行拿取，直到minHeap中的元素个数小于2。
//         维护变量sum，记录每次两个元素求和的结果。初始化为0。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public int connectSticks(int[] sticks) {
        if (sticks.length < 2) {
            return 0;
        }
        
        int sum = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n : sticks) {
            pq.offer(n);
        }
        
        while (pq.size() >= 2) {
            int curr = pq.poll();
            curr += pq.poll();
            sum += curr;
            pq.offer(curr);
        }
        
        return sum;
    }
}