https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/

idea: Prefix Sum (HashMap) + Greedy
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int ret = 0, end = -1, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();    // prefix sum -> last occurred position
        map.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (map.containsKey(sum - target) && map.get(sum - target) >= end) {
                // update end to denote the boundary of possible range
                ret++;
                end = i;
            }
            
            map.put(sum, i);
        }
        
        return ret;
    }
}