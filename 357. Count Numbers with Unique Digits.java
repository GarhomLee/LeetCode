https://leetcode.com/problems/count-numbers-with-unique-digits/

// 1）维护两个变量：count表示当前找到的所有unique digits的个数，temp表示在位数为i时的unique digits的个数
// 2）循环的边界为n和10的较小数，因为超过10位的数不会再有新的unique digits

class Solution {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        
        int count = 10, temp = 9;
        for (int i = 2; i <= Math.min(n, 10); i++) {
            temp = (9 - i + 2) * temp;
            count = temp + count;
        }
        return count;
    }
}