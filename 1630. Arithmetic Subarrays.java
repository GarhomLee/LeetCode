https://leetcode.com/problems/arithmetic-subarrays/

idea: Brute Force with Sort
time complexity: O(m * n log n)
space compleixty: O(n)

class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = nums.length, m = l.length;
        List<Boolean> res = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            int currLen = r[i] - l[i] + 1;
            if (currLen < 2) {
                res.add(false);
            } else {
                int[] copy = new int[currLen];
                for (int j = 0; j < currLen; j++) {
                    copy[j] = nums[l[i] + j];
                }
                
                Arrays.sort(copy);
                int diff = copy[1] - copy[0];
                boolean isArithmetic = true;
                for (int j = 1; j < currLen && isArithmetic; j++) {
                    isArithmetic = isArithmetic && (copy[j] - copy[j-1] == diff);
                }
                res.add(isArithmetic);
            }
        }
        
        return res;
    }
}