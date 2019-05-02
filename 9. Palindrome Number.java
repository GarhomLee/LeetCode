https://leetcode.com/problems/palindrome-number/

// 1）考虑corner cases：0一定为true，负数或者非0的10的倍数一定为false。这一步的判断是为了避免影响接下来用integer reversion。
// 2）用integer reversion模板得到reverse的数。这里有一个trick：不需要将x完全reverse，【只需要reverse一半即可】
// 3）由于只reverse了一半，当原来的x是奇数的时候，最后reverse会多一位数，所以还需判断x == reverse / 10

class Solution {
    public boolean isPalindrome(int x) {
        if (x == 0) return true;
        if (x < 0 || x % 10 == 0) return false;
        int reverse = 0;
        while (x > reverse) {
            reverse = reverse * 10 + x % 10;
            x = x /10;
        }
        return x == reverse || x == reverse / 10;
    }
}