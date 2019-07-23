https://leetcode.com/problems/total-hamming-distance/

// Brute Force：所有两两配对都求一遍Hamming Distance，时间O(n^2)
// 出现的问题：时间复杂度过高，超时。


// 优化思路：Bit Manipulation，可以看成求组合数问题。视频讲解：https://www.youtube.com/watch?v=fH9clXXrS2Q
//         可以发现，每个数的二进制表示的各个数位之间并没有直接联系，而Hamming Distance实际上来自于不同数的同一二进制数位。
//         因此，可以分成32个数位分别进行统计。
//         对于每个数位，只有0和1两种选择。假设n个数中当前数位有k个1，那么必然有n-k个0。对于每个1，都能形成n-k的距离，那么
//         当前数位总共能形成k*(n-k)的距离。实际上，就是C(k,1)*C(n-k,1)。
//         最后的结果是遍历32个数位后的总和。

class Solution {
    public int totalHammingDistance(int[] nums) {
        /* corner case */
        if (nums.length < 2) return 0;
        
        int res = 0;
        for (int i = 0; i < 32; i++) {
            int oneCount = 0;
            for (int n: nums) {
                oneCount += (n >> i) & 1;
            }
            res += oneCount * (nums.length - oneCount);
        }
        
        return res;
    }
}