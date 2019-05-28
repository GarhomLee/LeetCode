https://leetcode.com/problems/minimum-size-subarray-sum/

// 解法一：sliding window

// 1）维护left和right两个pointer形成sliding window，其中left指向当前range的最左边元素，初始化为0；right指向range右边还没扫描的第一个元素，初始化为0
// 2）维护临时变量sum用来和s比较，维护变量minLen作为返回结果，初始化为一个大的数
// 3）right依次扫描并加到sum里面
// 4）当sum >= s时，开始不断减去left的元素，同时移动left，直至sum < s，并更新minLen
// 5）如果minLen为初始化的数，未被改变，说明没有比s大的sum，返回0；否则返回minLen
// 时间复杂度：O(n)
// 空间复杂度：O(1)

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

// 解法二：Binary Search
//         遍历nums数组，分别以每一个元素为窗口左边界，nums数组最后一个元素为窗口右边界，找到最小的index，使得nums数组从当前左边界到这个index形成的sum大于等于s。
//         low boundary: nums数组每一个元素的index（1-based），即i
//         high boundary: nums数组最后一个元素的index（1-based），即nums.length
//         g(m):找到最小的mid，使得nums[i:mid)的和大于等于s对于所有比mid大的index都成立
//         返回值：窗口的长度minLen，因为是1-based，所以是low-i+1。在所有可能的窗口中取最小的一个。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)
// 犯错点：1.在取返回值更新窗口长度minLen前，需要判断找到的边界有没有超过nums数组的长度。如果超过了nums.length，sums[low]不存在，说明nums[i:length)的和不足以大于等于s。
//         改正方法：1）要么判断只有当找到的边界不超过nums数组的长度时才能用来更新minLen；2）要么在进行binary search前判断当前窗口中的和是否大于等于s

class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int[] sum = new int[nums.length + 1];
        int minLen = nums.length + 10;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        
        if (sum[nums.length] < s) return 0;  // corner case
        
        for (int i = 1; i <= nums.length; i++) {
            int low = i, high = nums.length;
            //if (sum[high] - sum[i - 1] < s) break;  // {Correction 1 version 2}

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (sum[mid] - sum[i - 1] >= s) high = mid - 1;
                else low = mid + 1;
            }
            
            //minLen = Math.min(minLen, low - i + 1);  // {Mistake 1: if low is beyond the boundary of sum array, it means that nums[i:length] is not sufficient to sum up to s}
            if (low <= nums.length) minLen = Math.min(minLen, low - i + 1);  // {Correction 1 version 1}
        } 
        
        return minLen;
    }
}