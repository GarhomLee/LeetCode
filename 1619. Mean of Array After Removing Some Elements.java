https://leetcode.com/problems/mean-of-array-after-removing-some-elements/

idea: Sort
time complexity: O(n log n)
space complexity: O(n) for mergesort

class Solution {
    public double trimMean(int[] arr) {
        Arrays.sort(arr);
        int n = arr.length, m = n / 20;
        double sum = 0.0;
        for (int i = m; i + m < n; i++) {
            sum += arr[i];
        }
        
        return sum / (n - 2*m);
    }
}