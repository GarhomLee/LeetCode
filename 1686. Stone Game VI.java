https://leetcode.com/problems/stone-game-vi/

idea: Greedy + Sort. Refer to: https://leetcode.com/problems/stone-game-vi/discuss/969574/JavaC%2B%2BPython-Sort-by-Value-Sum
time complexity: O(n log n)
space complexity: O(n)

class Solution {
    public int stoneGameVI(int[] aliceValues, int[] bobValues) {        
        int n = aliceValues.length;
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i][0] = aliceValues[i];
            pairs[i][1] = bobValues[i];
        }
        Arrays.sort(pairs, (a, b) -> a[0] + a[1] == b[0] + b[1] ? b[0] - a[0] : (b[0] + b[1]) - (a[0] + a[1]));
            
        int sumA = 0, sumB = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                sumA += pairs[i][0];
            } else {
                sumB += pairs[i][1];
            }
        }
        
        return Math.max(Math.min(sumA - sumB, 1), -1);
    }
}