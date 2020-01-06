https://leetcode.com/problems/subarray-sums-divisible-by-k/

思路：Math。视频讲解：https://www.youtube.com/watch?v=pkx6SowjL7M
        求得所有[0:i]的【前缀和除以K后的余数，且把余数都转化为非负数】，那么将任意两个余数相同的[0:i]
        的前缀和相减得到的subarray的和就能够被K整除，即对每种余数num的情况求组合数C(num, 2)。
时间复杂度：O(n + K), n=A.length
空间复杂度：O(K)
犯错点：1.初始化错误：当包含0个元素时，前缀和为0，需要初始化count[0]=1，使得再次出现前缀和为0的情况时
            组合数可以被计算。

class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int[] count = new int[K];
        // {Mistake 1}
        count[0] = 1; // {Correction 1}
        int sum = 0;
        for (int num : A) {
            sum += num;
            count[(sum % K + K) % K]++;
        }
        
        int res = 0;
        for (int num : count) {
            if (num >= 2) {
                res += num * (num - 1) / 2; // get combination C(num, 2)
            }
        }
        
        return res;
    }
}