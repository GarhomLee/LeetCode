https://leetcode.com/problems/minimum-size-subarray-sum/

// O(n)时间的解法：sliding window

// 1）维护left和right两个pointer形成sliding window，其中left指向当前range的最左边元素，初始化为0；right指向range右边还没扫描的第一个元素，初始化为0
// 2）维护临时变量sum用来和s比较，维护变量minLen作为返回结果，初始化为一个大的数
// 3）right依次扫描并加到sum里面
// 4）当sum >= s时，开始不断减去left的元素，同时移动left，直至sum < s，并更新minLen
// 5）如果minLen为初始化的数，未被改变，说明没有比s大的sum，返回0；否则返回minLen

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0;
        int sum = 0, minLen = nums.length + 10;
        while (right < nums.length) {
            sum += nums[right++];
            if (sum >= s) {
                while (left < right && sum >= s) {
                    minLen = Math.min(right - left, minLen);
                    sum -= nums[left++];
                }
            }
        }
        return minLen > nums.length ? 0 : minLen;
    }
}