https://leetcode.com/problems/subtract-the-product-and-sum-of-digits-of-an-integer/

思路：Math
时间复杂度：O(log n)
空间复杂度：O(1)

class Solution {
    public int subtractProductAndSum(int n) {
        int product = 1, sum = 0;
        while (n != 0) {
            int lastDigit = n % 10;
            n /= 10;
            product *= lastDigit;
            sum += lastDigit;
        }
        
        return product - sum;
    }
}