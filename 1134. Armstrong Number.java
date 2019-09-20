https://leetcode.com/problems/armstrong-number/

// 思路：Math，相关api的调用。
// 时间复杂度：O(k)
// 空间复杂度：O(1)

class Solution {
    public boolean isArmstrong(int N) {
        int k = String.valueOf(N).length();  // optimization, no need to manually iterate every digit to get k
        int temp = N;
        
        long sum = 0;
        while (temp > 0) {
            sum += Math.pow(temp % 10, k);
            temp = temp / 10;
        }
        
        return sum == N;
    }
}