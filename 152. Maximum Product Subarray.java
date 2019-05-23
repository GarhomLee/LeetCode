https://leetcode.com/problems/maximum-product-subarray/

// 解法一：属于dynamic programing，可以看作是53. Maximum Subarray的升级版。
// 1）维护currMax表示包含当前nums元素在内的局部最大值（局部的定义是从最近一个0元素到当前nums）；维护currMin表示包含当前nums元素在内的局部最小值；维护max表示全局最大值，最后返回之
// 2）在遍历array元素的过程中，不断更新currMax，currMin，和max。当nums < 0时，currMax * num会变为最小，currMin * num会变为最大因为currMax要取currMax * num，currMin * num，和nums三个数
//     的最大，同理currMin要取currMax * num，currMin * num，和nums三个数的最小，如果不做临时储存，那么更新了currMax必然会导致更新currMin时用以比较的数发生改变，导致错误。所以先
//     preMax = currMax，preMin =currMin，然后根据preMax * num，preMin * num，和nums来更新，避免currMax和currMin的纠缠产生的错误。
// 3）遍历过程中更新max。
4）Time complexity: O(n); Space complexity: O(1)

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int currMax = 1, currMin = 1;
        int max = Integer.MIN_VALUE;
        for (int n: nums) {
            int preMax = currMax, preMin = currMin;
            currMax = Math.max(n, Math.max(preMax * n, preMin * n));
            currMin = Math.min(n, Math.min(preMax * n, preMin * n));

            max = Math.max(max, currMax);
        }
        return max;
    }
}

// 解法二：解法一的另一种写法。可知currMax >= currMin，无论currMax和currMin是正是负。如果nums[i]<0，只有currMin一定有机会成为currMax，
//         所以先行将currMax和currMin交换，然后currMax取currMax * num和nums的最大，currMin取currMin * num和nums的最小，同样可以避免
//         由于currMax和currMin的纠缠产生的错误。这样只需在两个数之间比较即可。

class Solution {
    public int maxProduct(int[] nums) {
        if (nums.length == 0) return 0;
        
        int currMax = 1, currMin = 1;
        int max = Integer.MIN_VALUE;

        for (int n: nums) {
            if (n < 0) {
                int pre = currMax;
                currMax = currMin;
                currMin = pre;
            }

            int preMax = currMax, preMin = currMin;
            currMax = Math.max(n, currMax * n);
            currMin = Math.min(n, currMin * n);

            max = Math.max(max, currMax);
        }
        return max;
    }
}