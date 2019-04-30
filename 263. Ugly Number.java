https://leetcode.com/problems/ugly-number/

// 解法一：iteration，分别整除2，3，5直到不能整除，看余数是否为1

class Solution {
    public boolean isUgly(int num) {
        if(num <= 0) return false;
        if(num == 1) return true;
        while(num % 2 == 0) num = num / 2;
        while(num % 3 == 0) num = num / 3;
        while(num % 5 == 0) num = num / 5;
        return num == 1;   
    }
}

// 解法二：recursion

class Solution {
    public boolean isUgly(int num) {
        if (num <= 0) return false;
        if (num == 1 || num == 2 || num == 3 || num == 5) return true;
        return (num % 2 == 0 && isUgly(num / 2)) || (num % 3 == 0 && isUgly(num / 3)) || (num % 5 == 0 && isUgly(num / 5));
    }
}