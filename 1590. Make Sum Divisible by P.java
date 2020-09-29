https://leetcode.com/problems/make-sum-divisible-by-p/

idea: Prefix Sum. Refer to: https://leetcode.com/problems/make-sum-divisible-by-p/discuss/854174/C%2B%2BJava-O(n)
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int minSubarray(int[] nums, int p) {
        int n = nums.length, minLen = nums.length;
        int remainder = 0;
        for (int num : nums) {
            remainder = (remainder + num) % p;
        }
        
        if (remainder == 0) {
            return 0;
        }
        
        Map<Integer, Integer> map = new HashMap<>();    // remainder -> latest index
        map.put(0, -1);
        int curr = 0;
        for (int i = 0; i < n; i++) {
            curr = (curr + nums[i]) % p;
            int want = (curr - remainder + p) % p;
            if (map.containsKey(want)) {
                minLen = Math.min(minLen, i - map.get(want));
            }
            
            map.put(curr, i);
        }
        
        return minLen == n ? -1 : minLen;
    }
}