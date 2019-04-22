https://leetcode.com/problems/contains-duplicate/

// 解法一：Brute force，O(n2)时间，超时
// 解法二：sort，看是否有相邻两个数相等

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) return true;
        }
        return false;
    }
}

// 解法三：HashSet

class Solution {
    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) return false;
        Set<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) return true;
            set.add(nums[i]);
        }
        return false;
    }
}

