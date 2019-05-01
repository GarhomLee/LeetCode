https://leetcode.com/problems/excel-sheet-column-title/

// 实际上考察的是【进制转换】：十进制转换成26进制。
// 1）类似十进制转换成二进制，先转换出最低位，最后转换出最高位
// 2）要利用n % 26作为index的性质，需要将n变成0-based，所以需要n--
// 3）n除以进制的基数（即26）来更新n

class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            n--;
            sb.insert(0, (char)('A' + n % 26));
            n /= 26;
        }
        
        return sb.toString();
    }
}