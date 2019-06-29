https://leetcode.com/problems/implement-rand10-using-rand7/

// 思路：Rejection sampling
//      两次调用rand7()后，会出现49种排列结果。只需要利用前40个结果，再转化为[1:10]。因为这40个结果每一个值的概率相同，
//      所以最后可以以相同的概率得到[1:10]的某一个数。如果得到的是[41:49]，那么重新调用rand7()。
// 犯错点：1.第一次rand7()和第二次rand7()的结果分别作为行号和列号，得到对应的一维[1:49]的值，而不是直接相乘。

class Solution extends SolBase {
    public int rand10() {
        int temp = 0, first = rand7();
        while (temp <= 0 || temp > 40) {
            int second = rand7();
            //temp = first * second;  // {Mistake 1}
            temp = (first - 1) * 7 + second;  // {Correction 1}
            first = second;
        }
        
        return temp % 10 + 1;
    }
}