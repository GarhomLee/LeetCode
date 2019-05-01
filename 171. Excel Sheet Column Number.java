https://leetcode.com/problems/excel-sheet-column-number/

// 实际上考察的是【进制转换】：26进制转换成十进制。
// 类似二进制转换成十进制，先从最高位开始转换，最后处理最低位

class Solution {
    public int titleToNumber(String s) {
        int res = 0;
        for (char c: s.toCharArray()) {
            res = 26 * res + (c - 'A' + 1);
        }
        return res;
    }
}