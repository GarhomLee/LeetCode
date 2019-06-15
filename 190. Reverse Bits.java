https://leetcode.com/problems/reverse-bits/

// 思路：把n第i位（0-based）的bit放到res第31-i位。
// 注意：这里的reverse是指把二进制32位【左右镜像翻转】，不是0翻成1、1翻成0。
// 犯错点：1.不是放到res的第i位，否则就变成复制n了。

public class Solution {
    public int reverseBits(int n) {
        int res = 0;
        /* 写法一 */
        for (int i = 0; i < 32; i++) {
            int curr = n & 1;
            n = n >> 1;
            //res = res | (curr << i);  // {Mistake 1: this is copying n}
            res = res | (curr << 31 - i);  // {Correction 1}
        }

        /* 写法二 */
        /*for (int i = 0; i < 32; i++) {
            int curr = n & 1;
            n = n >> 1;
            res = (res << 1) + curr;  
        }*/

        return res;
    }
}
