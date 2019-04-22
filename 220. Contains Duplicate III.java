https://leetcode.com/problems/contains-duplicate-iii/

// 219. Contains Duplicate II的follow-up。

// 解法一：需要找某个特定范围内的数的差值，可以用TreeSet，因为它用balanced binary search tree实现，便于查找floor和ceiling，同时可以避免重复。
// 1）遍历数组，查找TreeSet中与其对应的floor和ceiling，如果有符合在差值范围内的，返回true；否则将这个数加入TreeSet
// 2）同时维护TreeSet的size，来保持index的范围也符合题意。遍历完毕，没有符合的数对，返回false。

class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (nums == null || nums.length == 0 || k <= 0 || t < 0) return false;
        TreeSet<Integer> treeSet = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Integer floor = treeSet.floor(nums[i]);
            if (floor != null && ((long) nums[i] - floor) <= t) return true;
            Integer ceiling = treeSet.ceiling(nums[i]);
            if (ceiling != null && ((long) ceiling - nums[i]) <= t) return true;
            treeSet.add(nums[i]);
            if (treeSet.size() > k) treeSet.remove(nums[i - k]);
        }
        return false;
    }
}

// 解法二：bucket sort，不是很懂。