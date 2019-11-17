https://leetcode.com/problems/greatest-sum-divisible-by-three/

// 解法一：Math + DP
//         所有的数字只有3种可能：能被3整除，被3除的余数为1，和被3除的余数为2。要得到能被3整除的最大和，那么
//         只需要得到所有数的总和res：
//         1）如果res能被3整除，直接返回；
//         2）如果res被3除的余数为1，那么减去所有元素中被3除的余数为1的最小元素（或元素和）；
//         3）如果res被3除的余数为2，那么减去所有元素中被3除的余数为2的最小元素（或元素和）。
//         因此，维护变量leftOne和leftTwo，表示余数为1和2的最小元素（或元素和）。在遍历nums数组时，不断更新
//         leftOne和leftTwo。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int maxSumDivThree(int[] nums) {
        int res = 0, leftOne = 20000, leftTwo = 20000;
        for(int n:nums){
            res+=n;
            if(n%3==1){
                leftTwo = Math.min(leftTwo,leftOne+n);
                leftOne=Math.min(leftOne,n);
                // leftTwo = Math.min(leftTwo,leftOne+n);
            }
            if(n%3==2) {
                leftOne = Math.min(leftOne,leftTwo+n);
                leftTwo=Math.min(leftTwo,n);
                // leftOne = Math.min(leftOne,leftTwo+n);
            }
        }
        if(res%3==0) return res;
        if(res%3==1) return res-leftOne;
        return res - leftTwo;
        
    }
}


解法二：Math + DP。参考：https://leetcode.com/problems/greatest-sum-divisible-by-three/discuss/431077/Python-One-Pass-O(1)-space

时间复杂度：O(n)
空间复杂度：O(1)

class Solution {
    public int maxSumDivThree(int[] A) {
        int[] dp = new int[]{0, Integer.MIN_VALUE, Integer.MIN_VALUE};
        for (int a : A) {
            int[] dp2 = new int[3];
            for (int i = 0; i < 3; ++i)
                dp2[(i + a) % 3] = Math.max(dp[(i + a) % 3], dp[i] + a);
            dp = dp2;
        }
        return dp[0];
    }
}