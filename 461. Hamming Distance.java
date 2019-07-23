https://leetcode.com/problems/hamming-distance/submissions/

// 思路：Bit Manipulation。题目已经给出明显提示，要进行bit的操作。
//         首先，利用xor得到所有差异的bit。然后，不断利用x & (x - 1)消除最后一个1bit，同时dist++。最后返回dist。

class Solution {
    public int hammingDistance(int x, int y) {
        x = x ^ y;
        int dist = 0;
        while (x != 0) {
            dist++;
            x = x & (x - 1);
        }
        return dist;
    }
}

// 代码简化：利用内置函数Integer.bitCount()

class Solution {
    public int hammingDistance(int x, int y) {
        return Integer.bitCount(x ^ y);
    }
}