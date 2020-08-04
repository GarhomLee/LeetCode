https://leetcode.com/problems/find-the-winner-of-an-array-game/

idea: Simulation
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int getWinner(int[] arr, int k) {
        int n = arr.length;
        int max = arr[0], count = 0;
        for (int i = 1; i < n && count < k; i++) {
            if (max > arr[i]) {
                count++;
            } else if (max < arr[i]) {
                count = 1;
                max = arr[i];
            }
        }
        
        return max;
    }
}