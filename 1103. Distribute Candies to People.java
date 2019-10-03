https://leetcode.com/problems/distribute-candies-to-people/

// 思路：Brute Force
// 时间复杂度：O(sqrt(candies))，因为1+2+3+...+k = k*(k+1)/2 = candies，一共k步，k和sqrt(candies)有关。
// 空间复杂度：O(n)

class Solution {
    public int[] distributeCandies(int candies, int n) {
        int[] res = new int[n];
        int pos = 0;
        while (candies > 0) {
            int index = pos % n;
            int curr = Math.min(candies, pos + 1);;
            res[index] += curr;
            candies -= curr;
            pos++;
        }
        
        return res;
    }
}