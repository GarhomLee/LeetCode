https://leetcode.com/problems/remove-duplicates-from-sorted-array/

// Two pointers
// 1）left指向最后一个去重后的元素，right指向第一个未被扫描的元素
// 2）当nums[left] != nums[right]，把nums[right]插入到left的下一位，更新left和right。否则直接更新right

class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 1;
        while (right < nums.length) {
            if (nums[right] != nums[left]) nums[++left] = nums[right];
            right++;
        }
        return left + 1;
    }
}