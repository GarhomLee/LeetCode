https://leetcode.com/problems/largest-divisible-subset/

// 属于Dynamic programming。
// 1）先对nums进行排序。
// 2）维护dp数组，dp[i]表示从0到i包含nums[i]的最大子集长度；维护maxLen表示找到的全局最大子集长度，maxIndex表示找到maxLen的位置
// 3）对于每个nums[i]，都要搜索所有之前的可以被整除的元素，找到最大的dp，然后+1表示把当前nums[i]加入
// 4）再次遍历数组，从后往前找到能整除nums[maxIndex]的数。【维护currDP】表示与之相关的dp，每当找到一个数，加入list，同时currDP--

class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length <= 0) return list;
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        dp[0] = 1;
        int maxLen = 1, maxIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] % nums[j] == 0) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] > maxLen) {
                        maxLen = dp[i];
                        maxIndex = i;
                    }
                }
            }
        }
        
        int currDP = dp[maxIndex];
        for (int i = nums.length - 1; i >= 0 ; i--) {
            if (nums[maxIndex] % nums[i] == 0 && currDP == dp[i]) {
                list.add(nums[i]);
                currDP--;
            }
        }
        return list;
    }
}