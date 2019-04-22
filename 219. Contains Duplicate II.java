https://leetcode.com/problems/contains-duplicate-ii/

// 217. Contains Duplicate的follow-up，增加条件nums == nums[j]且j - i <= k。由于对index有要求，不能改变index，所以不能sort
// 1）维护一个HashMap，key为nums，value为index
// 2）如果HashMap存在nums且j - i <= k，返回true
// 3）否则，put key and value
// 4）遍历完成，跳出循环，返回false
// 5）Time complexity: O(n);Space complexity: O(n)

class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) return false;
        Map<Integer, Integer> indexMapping = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (indexMapping.containsKey(nums[i]) && i - indexMapping.get(nums[i]) <= k) return true;
            else indexMapping.put(nums[i], i);
        }
        return false;
    }
}