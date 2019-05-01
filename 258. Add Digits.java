https://leetcode.com/problems/add-digits/

// 解法一：recursion/iteration

// 解法二：考数学性质，没啥好说，背公式背结论。

class Solution {
    public int addDigits(int num) {
        if (num == 0) return 0;
        if (num % 9 == 0) return 9;
        return num % 9;
    }
}