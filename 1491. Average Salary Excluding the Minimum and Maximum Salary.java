https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/

idea: 1-pass iteration
time complexity: O(n)
space complexity: O(1)

class Solution {
    public double average(int[] salary) {
        int max = 0, min = Integer.MAX_VALUE, sum = 0, n = salary.length;
        if (n < 3) return 0;
        
        for (int num : salary) {
            sum += num;
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
        
        return (sum - max - min) / ((double) n - 2);
    }
}