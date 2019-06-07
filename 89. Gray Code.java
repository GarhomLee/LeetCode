https://leetcode.com/problems/gray-code/

// 总体思路：参考https://www.jianshu.com/p/88d5b6362f0b，需要知道数字规律。
//         如果已知n-1位的序列，那么n位的序列求法是有规律的，比如2位的序列[00,01,11,10]变成3位的话只需要先在高位加0变成
//         [000,001,011,010]，再逆序在高位加1，即[110,111,101,100]，合起来就是3位的序列了，[000,001,011,010,110,111,101,100]。
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
// 犯错点：1.注意1左移的位数，应该跟上一层数字的最长位数相同

class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> res = new ArrayList();
        res.add(0);

        /* 写法一 */
        for (int i = 0; i < n; i++) {  // getting results of 2^(i+1)
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                res.add(res.get(j) | (1 << i)); 
            }
        }

        /* 写法二 */
        /*for (int i = 1; i <= n; i++) {  // getting results of 2^i
            int size = res.size();
            for (int j = size - 1; j >= 0; j--) {
                //res.add(res.get(j) | (1 << i));  // {Mistake 1}
                res.add(res.get(j) | (1 << (i - 1)));  // {Correction 1}
            }
        }*/
        
        return res;
    }
}