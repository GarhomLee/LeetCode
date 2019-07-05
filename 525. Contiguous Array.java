https://leetcode.com/problems/contiguous-array/

// 思路：HashMap，将0看作-1，就可以转化成560. Subarray Sum Equals K的类似问题，利用到prefix sum。
//      HashMap的key为所有的[0:i]的和，value为这个和出现的【最短长度】，即从左到右【第一次出现时】的长度。
//      遍历nums数组，不断更新变量sum表示[0:i]的和，出现0时加-1，出现1时仍然加1。有三种可能情况：
//      1）sum==0，意味着nums[0:i]中0和1数量相等，符合题意，此时的长度一定为当前能得到的最长长度，因此更新maxLen。
//      2）sum!=0，那么就查找是否出现prefix sum，使得k==0，即sum-k==sum，即HashMap中是否已经出现了sum。
//         如果HashMap中没有sum，说明是第一次出现，因此存入HashMap中。
//      3）如果HashMap中已经有sum，那么k==0的长度可以根据当前位置i和HashMap中和sum对应的长度求得。不需要更新HashMap。
// 关键点：将0看作-1，就可以变成判断和为0的最长连续子序列长度
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int findMaxLength(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        Map<Integer, Integer> map = new HashMap();
        int maxLen = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i] == 0 ? -1 : 1;
            sum += curr;
            if (sum == 0) {
                maxLen = i + 1;
            } else if (!map.containsKey(sum)) {
                map.put(sum, i + 1);
            } else {
                maxLen = Math.max(maxLen, i + 1 - map.get(sum));
            }
        }
        
        return maxLen;
    }
}