https://leetcode.com/problems/remove-element/

// Two pointers
// 1）left指向第一个可插入的位置，right指向第一个未被扫描的位置
// 2）只有当nums[right] != val时才将其赋值到left处

class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            if (nums[right] != val) nums[left++] = nums[right];
            right++;
        }
        return left;
    }
}