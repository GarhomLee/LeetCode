https://leetcode.com/problems/find-pivot-index/

// 思路：Prefix Sum
//         两次遍历。
//         第一次：求所有数的和sum。
//         第二次：维护变量left，表示当前位置i的左边[0:i)的部分和，同理right表示[i+1:n)的部分和。
//             如果left==right，返回i；否则，更新left+=nums[i]。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        
        int left = 0; // left sum
        for (int i = 0; i < nums.length; i++) {
            int right = sum - nums[i] - left; // right sum
            if (left == right) {
                return i;
            }
            
            left += nums[i];
        }
        
        return -1;
    }
}