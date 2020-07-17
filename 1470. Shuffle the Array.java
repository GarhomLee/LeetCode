https://leetcode.com/problems/shuffle-the-array/

solution1: 1-pass
time complexity: O(n)
space complexity: O(n)

class Solution {
    public int[] shuffle(int[] nums, int n) {
        int[] ret = new int[2 * n];
        for (int i = 0; i < 2 * n; i++) {
            ret[i] = i % 2 == 0 ? nums[i / 2] : nums[i / 2 + n];
        }
        
        return ret;
    }
}


solution2: 2-pass Bit manipulaion