https://leetcode.com/problems/number-of-good-pairs/

idea: Count
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int numIdenticalPairs(int[] nums) {
        int[] count = new int[101];
        int ret = 0;
        for (int num : nums) {
            ret += count[num];
            count[num]++;
        }
        
        return ret;
    }
}