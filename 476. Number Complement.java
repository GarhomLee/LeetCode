https://leetcode.com/problems/number-complement/

// 思路：Bit Manipulation
//         先求出num二进制中最高位1的位置，对于从这个位置开始到最右边所有位置都填上1，记为sum，实际上sum就是num和题目
//         要求的complement number做异或后的结果。同样，用sum和num做异或，也可以得到题目要求的complement number。
// 注意：题目规定了num为正数，因此不需要对num==0特殊讨论。

class Solution {
    public int findComplement(int num) {
        int sum = 0, temp = num;
        int i = 0;
        while (temp != 0) {
            sum |= (1 << i);
            i++;
            temp = temp >> 1;
        }
        return sum ^ num;
    }
}

另一种写法：
public int findComplement(int num) {
        int highestOneBit = Integer.highestOneBit(num);
        return num ^ (highestOneBit | highestOneBit - 1); 
    }

代码简化：见https://leetcode.com/problems/number-complement/discuss/95992/Java-1-line-bit-manipulation-solution