https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/

解法一：Prefix Sum
        根据题意，对每一个长度为L的subarray，分别遍历前后的长度为M的subarray，求最大和。
时间复杂度：O(n^2)
空间复杂度：O(n)

class Solution {
    public int maxSumTwoNoOverlap(int[] A, int L, int M) {
        int len = A.length;
        int[] sums = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            sums[i] = sums[i - 1] + A[i - 1];
        }
        
        int max = 0;
        for (int i = L; i <= len; i++) {
            int sum1 = sums[i] - sums[i - L];
            for (int j = M; j <= i - L; j++) {
                int sum2 = sums[j] - sums[j - M];
                max = Math.max(max, sum1 + sum2);
            }
            
            for (int j = i + M; j <= len; j++) {
                int sum2 = sums[j] - sums[j - M];
                max = Math.max(max, sum1 + sum2);
            }
        }
        
        return max;
    }
}


解法二：Prefix Sum + Dynamic Programming。参考：https://leetcode.com/problems/maximum-sum-of-two-non-overlapping-subarrays/discuss/278251/JavaC%2B%2BPython-O(N)Time-O(1)-Space

时间复杂度：O(n)
空间复杂度：O(n)或O(1)，取决于用不用额外数组存prefix sum
