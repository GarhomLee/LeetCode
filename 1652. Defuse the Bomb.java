https://leetcode.com/problems/defuse-the-bomb/

solution1: Brute Force
time complexity: O(n*k)
space complexity: O(1)

class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] res = new int[n];
        if (k == 0) {
            return res;
        }
        
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = 1; j <= Math.abs(k); j++) {
                int idx = k < 0 ? (i + n - j) % n : (i + n + j) % n;
                sum += code[idx];
            }
            res[i] = sum;
        }
        
        return res;
    }
}

solution2: Sliding Window
time complexity: O(n)
space complexity: O(1)