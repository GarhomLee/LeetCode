https://leetcode.com/problems/maximum-length-of-subarray-with-positive-product/

idea: DP
    -Divide into subarrays by element 0.
    -Count negative num in subarray. If even count, update maxLen by currLen; if odd count, update 
     maxLen by discarding the portion before the first negative num or after the last negative num,
     ie. max len of [startIdx:lastNeg] or [firstNeg:i].
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int getMaxLen(int[] nums) {
        int maxLen = 0, currLen = 0, n = nums.length; 
        int firstNeg = -1, lastNeg = -1, negCount = 0;
        for (int i = 0; i <= n; i++) {
            if (i == n || nums[i] == 0) {
                if (negCount % 2 == 0) {
                    maxLen = Math.max(maxLen, currLen);
                } else {
                    int startIdx = i - currLen;
                    maxLen = Math.max(maxLen, Math.max(lastNeg - startIdx, i - (firstNeg + 1)));
                }
                
                currLen = 0;
                firstNeg = -1;
                lastNeg = -1;
                negCount = 0;
            } else if (nums [i] < 0) {
                negCount++;
                currLen++;
                if (firstNeg < 0) {
                    firstNeg = i;
                    lastNeg = i;
                } else {
                    lastNeg = i;
                }
            } else {
                currLen++;
            }
        }
        return maxLen;
    }
}