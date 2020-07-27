https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/

idea: Segment Tree? Referring to: https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/discuss/754674/JavaC%2B%2BPython-Comparison-of-Consecutive-Elements
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int minNumberOperations(int[] target) {
        int ret = 0, pre = 0;
        for (int num : target) {
            ret += Math.max(0, num - pre);
            pre = num;
        }
        
        return ret;
    }
}