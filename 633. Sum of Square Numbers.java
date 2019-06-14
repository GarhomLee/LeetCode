https://leetcode.com/problems/sum-of-square-numbers/

// 思路：先确定其中一个加数为平方数，判断另外一个数能否开平方为整数。
// 时间复杂度：O(sqrt(n) * log(n))
// 空间复杂度：O(1)
// 犯错点：1.条件判断i * i <= c / 2要取等，否则会漏掉c = 0和c = 1。事实上，除以2是不必要的。
//        2.Math.sqrt()返回double，要强制转换为int

class Solution {
    public boolean judgeSquareSum(int c) {
        //for (int i = 0; i * i < c / 2; i++)  // {Mistake 1: it skips c = 0 and c = 1}
        for (int i = 0; i * i <= c / 2; i++) {  // {Correction 1}
            int remain = c - i * i;
            //int sqrt = Math.sqrt(remain);  // {Mistake 2: Math.sqrt() returns a double}
            int sqrt = (int) Math.sqrt(remain);  // {Correction 2}
            if (sqrt * sqrt == remain) return true;
        }
        return false;
    }
}