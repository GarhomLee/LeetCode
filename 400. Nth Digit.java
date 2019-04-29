https://leetcode.com/problems/nth-digit/

// 1）维护变量：len，表示当前搜寻的数的位数；count，表示位数为len的数的个数，注意【设为long避免越界】；startNum，表示第一个位数为len的数
// 2）每次循环，n减少len * count，即位数为len的所有digit的个数，同时更新count，startNum，len
// 3）根据startNum和剩下的n找到targetNum，转换为String，找到对应的digit

class Solution {
    public int findNthDigit(int n) {
        int len = 1;
        long count = 9;
        int startNum = 1;
        while (n > len * count) {
            n -= len * count;
            count *= 10;
            startNum *= 10;
            len++;
        }
        int targetNum = startNum + (n - 1) / len;
        String s = Integer.toString(targetNum);
        return s.charAt((n - 1) % len) - '0';
    }
}