https://leetcode.com/problems/sum-of-digits-in-the-minimum-number/

// 思路：Math
// 时间复杂度：O(len + log(num)), len=A.length, num=max(A)
// 空间复杂度：O(1)

class Solution {
    public int sumOfDigits(int[] A) {
        int num = A[0];
        for (int n: A) {
            num = Math.min(num, n);
        }
        int sum = 0;
        while (num != 0) {
            sum += num % 10;
            num /= 10;
        }
        
        return (sum + 1) % 2;
    }
}