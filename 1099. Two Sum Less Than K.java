https://leetcode.com/problems/two-sum-less-than-k/

// 思路：Sort + Two pointers (low & high)。类似k sum问题中简化成2 sum的情况。
//         维护指针变量left和right；同时维护变量maxSum表示当前搜索到的比K小的最大2 sum结果，初始化为-1。
//         当left < right时，比较sum = A[left] + A[right]和目标K。可能有两种情况：
//         1）当sum < K，符合题意，用sum更新maxSum。然后left++，尝试搜索更大的sum。
//         2）当sum >= K，说明sum太大，right--尝试搜索更小的sum。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n) for sorting, O(1) for searching

class Solution {
    public int twoSumLessThanK(int[] A, int K) {
        Arrays.sort(A);
        int left = 0, right = A.length - 1;
        int maxSum = -1;
        while (left < right) {
            int sum = A[left] + A[right];
            if (sum < K) {
                maxSum = Math.max(maxSum, sum);
                left++;
            } else {
                right--;
            }
        }
        
        return maxSum;
    }
}