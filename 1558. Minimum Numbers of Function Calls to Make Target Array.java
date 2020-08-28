https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/

idea: Bit Manipulation. Refer to: https://leetcode.com/problems/minimum-numbers-of-function-calls-to-make-target-array/discuss/805740/JavaC%2B%2BPython-Bit-Counts
time complexity: O(n log m), n=nums.length, m=max(nums)
space complexity: O(1)

class Solution {
    public int minOperations(int[] nums) {
        int maxLen = 0, oneCount = 0;
        Arrays.sort(nums);
        for (int num : nums) {
            int len = 0;
            while (num > 0) {
                oneCount += (num & 1);
                num >>= 1;
                len++;
            }
            maxLen = Math.max(maxLen, len);
        }
        
        return Math.max(oneCount + (maxLen - 1), 0);    // cannot be less than 0
    }
}