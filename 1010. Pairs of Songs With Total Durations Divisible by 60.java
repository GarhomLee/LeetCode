https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/

思路：Array模拟HashMap
时间复杂度：O(n)
空间复杂度：O(60) = O(1)

class Solution {
    public int numPairsDivisibleBy60(int[] time) {
        int[] count = new int[60];
        int res = 0;
        for (int t : time) {
            t = t % 60;
            res += count[(60 - t) % 60];
            count[t]++;
        }
        
        return res;
    }
}