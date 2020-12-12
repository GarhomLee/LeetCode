https://leetcode.com/problems/max-number-of-k-sum-pairs/

idea: HashMap. Two Sum follow-up.
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int maxOperations(int[] nums, int k) {
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int comp = k - num;
            if (map.containsKey(comp) && map.get(comp) > 0) {
                res++;
                map.put(comp, map.get(comp) - 1);
            } else {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
        }
        
        return res;
    }
}