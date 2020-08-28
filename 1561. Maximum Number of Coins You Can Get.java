https://leetcode.com/problems/maximum-number-of-coins-you-can-get/

idea: Greedy + Sort
    -Use greedy algo, so that the largest is for Alice, the second largest for myself, and the smallest
     for Bob which will be all 1/3 smallest elements after sorting.
time complexity: O(n log n)
space complexity: O(n) for sorting

class Solution {
    public int maxCoins(int[] piles) {
        int n = piles.length, res = 0;
        Arrays.sort(piles);
        for (int count = 1, idx = n - 2; count <= n / 3; count++, idx -= 2) {
            res += piles[idx];
        }
        
        return res;
    }
}