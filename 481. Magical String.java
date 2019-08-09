https://leetcode.com/problems/magical-string/

// 思路：Two Pointers，找规律。参考：https://leetcode.com/problems/magical-string/discuss/96413/Simple-Java-solution-using-one-array-and-two-pointers
//         维护两个指针：left，指向当前数字num需要重复的次数，初始化为2，表示指向res[2]；right，指向可以放置num的第一个位置，
//         初始化为3，表示指向res[3]。同时，num初始化为1，因为1和2交替出现，而res[1]和res[2]都是2
//         初始化res数组，放置所有1和2。注意：长度需要比n稍大，避免放置2个连续num的时候造成越界。
//         right指针遍历n个数，每次放置res[left]个数字num，如果num == 1且right没有越界，统计1出现的次数count++。更新right++。
//         放置完，更新left++，同时num在1和2之间翻转。利用xor可以完成翻转
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.初始化错误：当left为初始值2时，res[left]要有初始值2

class Solution {
    public int magicalString(int n) {
        if (n == 0) return 0;
        if (n <= 3) return 1;
        
        int[] res = new int[n + 10];
        int count = 1;  // count the number of 1s
        int left = 2, right = 3, num = 1;  // initialize the left pointer, the right pointer, and the num to add
        // {Mistake 1}
        res[0] = 1;  // this is unnecessary
        res[1] = 2;  // this is unnecessary
        res[2] = 2;  // {Correction 1}
        while (right < n) {
            for (int i = 0; i < res[left]; i++) {
                if (num == 1 && right < n) {  // count the valid 1s
                    count++;
                }
                res[right++] = num;
            }
            left++;
            num ^= 3;  // trick to flip num between 1 and 2
        }
        
        return count;
    }
}