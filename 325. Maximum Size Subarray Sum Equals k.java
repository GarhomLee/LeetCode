https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/

// 思路：Prefix Sum + Hash Table，注意subarray是【连续的元素】。
//         得到prefix sum后，用HashMap存每个sum第一次出现的位置。如果HashMap中存在sum[i] - k，那么
//         用HashMap中的sum[i] - k的位置和当前位置i的差值更新最大距离max。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int max = 0;
        for (int i = 1; i <= nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
            int diff = sum[i] - k;
            if (map.containsKey(diff)) {
                max = Math.max(max, i - map.get(diff));
            }
            map.putIfAbsent(sum[i], i);
        }
        
        return max;
    }
}