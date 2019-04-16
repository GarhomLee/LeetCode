https://leetcode.com/problems/missing-number/

// 解法一：sort
// 解法二：HashSet
// 解法三：XOR，观察得知index和nums[index]都成对出现，除了缺失的数。将nums.length作为最后一个index补足后，
//     利用XOR的性质，转化为136题Single Number问题。

class Solution {
    public int missingNumber(int[] nums) {
        int res = nums.length;
        for (int i = 0; i < nums.length; i++) {
            res ^= (i ^ nums[i]);
        }
        return res;
    }
}