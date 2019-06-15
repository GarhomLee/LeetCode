https://leetcode.com/problems/single-number-ii/

// 初步思路：HashMap
// 时间复杂度：O(n)
// 空间复杂度：O(n)

// 优化思路：Bit Manipulation
// 时间复杂度：O(n)
// 空间复杂度：O(1)

// 解法一：视频讲解https://www.youtube.com/watch?v=puXcQpwgcD0
//        数二进制中（32位）每一个位置的1。根据题意，每一个位置的1可能有3n个或3n+1个，多出的那一个1来自single number。
//        因此，遍历每一个位置，得到所有元素在这个位置的1的个数。如果个数除以3以后余出1，说明single number在这个位置有1，
//        所以把这个1移位到相应位置，然后用“|”操作加到这个位置上。（由于题目的特殊性，可以直接写成res = res | (count << i);）
// 犯错点：1.在取每个位置的1的时候，如果把1左移i位，“&”操作后就算当前位置有1，也不一定整体结果>0，因为如果1处在最高位，表示的是负数。
//         解决方法：1）可以把判断条件变为!=0；2）或者将n右移i位，整体结果必然为0或1

class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int n: nums) {
                //if ((n & (1 << i)) > 0) count++;  // {Mistake 1: negative number}
                //if ((n & (1 << i)) != 0) count++;  // {Correction 1}
                if (((n >>> i) & 1) > 0) count++;  // {AnotherCorrection 1}
            }
            count = count % 3;
            if (count > 0) res = res | (1 << i);
        }
        return res;
    }
}

// 解法二：不是很懂

class Solution {
    public int singleNumber(int[] nums) {
        int x1 = 0, x2 = 0, mask = 0;
         
        for (int i : nums) {
            x2 ^= x1 & i;
            x1 ^= i;
            mask = ~(x1 & x2);
            x2 &= mask;
            x1 &= mask;
        }

        return x1;  // Since p = 1, in binary form p = '01', then p1 = 1, so we should return x1. 
                    // If p = 2, in binary form p = '10', then p2 = 1, and we should return x2.
                    // Or alternatively we can simply return (x1 | x2).
    }
}