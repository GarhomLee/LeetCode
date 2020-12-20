https://leetcode.com/problems/split-array-with-equal-sum/

idea: Prefix Sum + Hash Set
time complexity: O(n^2)
space complexity: O(n)

class Solution {
    public boolean splitArray(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n+1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
        
        for (int j = 3; j <= n - 4; j++) {
            Set<Integer> seen = new HashSet<>();
            // add subarray sum if nums[0:i-1] == nums[i+1:j-1]
            for (int i = 1; i <= j - 2; i++) {
                if (sum[i] == sum[j] - sum[i+1]) {
                    seen.add(sum[i]);
                }
            }
            
            // check if nums[j+1:k-1] == nums[k+1:n-1] and the subarray sum exists
            for (int k = j + 2; k <= n - 2; k++) {
                if (sum[k] - sum[j+1] == sum[n] - sum[k+1] 
                    && seen.contains(sum[k] - sum[j+1])) {
                    return true;
                }
            }
        }
        
        return false;
    }
}