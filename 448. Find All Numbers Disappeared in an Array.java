https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/

// 解法一：
// 1）遍历数组，将value当成index，标记出现过的位置，标记成负数，那么没有出现过的位置对应的value不会被标记，还是正数
// 2）再次遍历数组，找到正数value对应的位置

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) nums[index] = -nums[index];
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) list.add(i + 1);
        }
        return list;
    }
}

// 解法二：套用模板。
// 1）遍历数组，将所有元素放到该放的位置
// 2）再次遍历数组，如果位置和元素对应不上，意味着找到了缺失的元素

class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (i != nums[i] - 1) list.add(i + 1);
        }
        return list;
    }
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}