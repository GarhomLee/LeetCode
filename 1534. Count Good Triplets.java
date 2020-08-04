https://leetcode.com/problems/count-good-triplets/

idea: Brute Force
time complexity: O(n^3)
space complexity: O(1)

class Solution {
    public int countGoodTriplets(int[] arr, int a, int b, int c) {
        int n = arr.length, ret = 0;
        for (int i = 0; i + 2 < n; i++) {
            for (int j = i + 1; j + 1 < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (Math.abs(arr[i] - arr[j]) <= a 
                        && Math.abs(arr[j] - arr[k]) <= b
                        && Math.abs(arr[i] - arr[k]) <= c) {
                        ret++;
                    }
                }
            }
        }
        
        return ret;
    }
}