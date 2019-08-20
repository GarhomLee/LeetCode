https://leetcode.com/problems/max-consecutive-ones-iii/

// 思路：Sliding Window
//         维护变量：zeroCount，记录当前可被替换的0的个数；maxLen，记录全局最长长度。
//         窗口的左右边缘分别用left和right指针指示。
//         step1: 遍历A数组，如果当前元素A[right]为0，那么zeroCount--；
//         step2: 每一个循环中都检查，如果出现zeroCount < 0，那么需要移动左边缘left，如果A[left] == 0，更新zeroCount++，
//                 直到zeroCount == 0
//         step3: 每一个循环中，调整好窗口大小后，用当前窗口长度right - left + 1更新maxLen
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int longestOnes(int[] A, int K) {
        int zeroCount = K, maxLen = 0;
        for (int left = 0, right = 0; right < A.length; right++) {
            if (A[right] == 0) {
                zeroCount--;
            }
            
            while (zeroCount < 0 && left <= right) {
                if (A[left] == 0) {
                    zeroCount++;
                }
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        
        return maxLen;
    }
}