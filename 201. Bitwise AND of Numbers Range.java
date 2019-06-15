https://leetcode.com/problems/bitwise-and-of-numbers-range/

// 思路：Bit Manipulation

// Brute Force：将从m到n的二进制32位中每一位都&1，然后把结果放置到相应位置
// 时间复杂度：O(32*range)，超时
// 空间复杂度：O(1)

// 优化：观察到，Bitwise AND的结果只和m和n的相同最高位有关

// 写法一：先记录最高位在哪一位，然后从最高位开始循环判断m和n在当前位是否相等。如果相等，把该位的数（0或1）放到相应位置；如果不相等，跳出循环。
// 犯错点：1.如果直接用n来判断位数，那么n被改变，后面循环会出错
class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int res = 0;
        
        int bitLen = 0;
        /*while (n > 0) {
            bitLen++;
            n = n >> 1;
        }*/

        int temp = n;  // {Correction 1}
        while (temp > 0) {
            bitLen++;
            temp = temp >> 1;
        }
        
        for (int i = bitLen - 1; i >= 0; i--) {
            if (((m >> i) & 1) != ((n >> i) & 1)) {
                break;
            }
            int curr = (n >> i) & 1;
            res = res | (curr << i) ;
        }
        
        return res;
    }
}

// 写法二：不断移除m和n的低位，直到m和n相等，然后把n移回原来位置，返回n

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        int count = 0;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            count++;
        }
        n = n << count;
        return n;
    }
}

// 写法三：不断移除n的最低位1，直到n<=m，然后取n&m，然后取n

class Solution {
    public int rangeBitwiseAnd(int m, int n) {
        while(n > m) {
            n = n & (n-1);
        }
        return(m & n);
    }
}