https://leetcode.com/problems/max-consecutive-ones/

// 思路：常规遍历，取最大值
// 时间复杂度：O(n)
// 空间复杂度：O(1)
// 犯错点：1.细节错误：要取count的最大值，记录在maxCount，而不是取最后的count

class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int count = 0, maxCount = 0;
        for (int n: nums) {
            count = n == 1 ? count + 1 : 0;
            maxCount = Math.max(maxCount, count);
        }
        return maxCount;
    }
}