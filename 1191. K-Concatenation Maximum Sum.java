https://leetcode.com/problems/k-concatenation-maximum-sum/

// 对比：53. Maximum Subarray (Kadanes Algorithm)，本题允许array重复k次。

// 思路：Kadanes Algorithm + 发现规律
//         step1: 如果k==1，就是53. Maximum Subarray的结果。
//         step2: 如果k>1，那么先求出arr数组的总和sum，然后再求出从后到前suffixSum的最大值，
//             和从前到后prefixSum的最大值。会出现两种情况：
//             1）sum <= 0，那么说明不必将k个array的sum加在一起，只需要比较k==1时的maximum subarray，
//                 记为kadanes，和k==2时将suffixSum的最大值和prefixSum的最大值拼起来的和，取较大的数。
//             2）sum > 0，那么将k个array的sum加在一起一定会比1个array的sum大，但是要最大化结果，实际上
//                 只需要将中间k-2个array的sum加在一起，再加上第一个array的suffixSum的最大值，和最后一个
//                 array的prefixSum的最大值。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    static final int MOD = 1_000_000_007;
    
    public int kConcatenationMaxSum(int[] arr, int k) {
        long kadanes = kadanes(arr);
        if (k == 1) {
            return (int) (kadanes % MOD);
        }
        
        long sum = 0;
        for (int num: arr) {
            sum += num;
        }
        
        long suffixSum = suffixSum(arr);
        long prefixSum = prefixSum(arr);
        
        if (sum <= 0) {
            return (int) Math.max(kadanes % MOD, suffixSum % MOD + prefixSum % MOD);
        }
        
        return (int) (suffixSum % MOD + prefixSum % MOD + ((k - 2) * sum) % MOD);
    }
    
    private long prefixSum(int[] arr) {
        long sum = 0, maxSum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
    
    private long suffixSum(int[] arr) {
        long sum = 0, maxSum = 0;
        for (int i = arr.length - 1; i >= 0; i--) {
            sum += arr[i];
            maxSum = Math.max(maxSum, sum);
        }
        return maxSum;
    }
    
    private long kadanes(int[] arr) {
        long maxSum = 0, currSum = Integer.MIN_VALUE;
        for (int num: arr) {
            currSum = Math.max(currSum + num, num);
            maxSum = Math.max(maxSum, currSum);
        }
        
        return maxSum;
    }
}