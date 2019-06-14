https://leetcode.com/problems/maximum-gap/

// 解法一：Sort，时间复杂度不符合题意
// 时间复杂度：O(n log n)
// 空间复杂度：O(1)

class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;

        Arrays.sort(nums);
        int maxDiff = 0;
        for (int i = 1; i < nums.length; i++) {
            maxDiff = Math.max(maxDiff, nums[i] - nums[i - 1]);
        }
        return maxDiff;
    }
}


// 解法二：Bucket Sort，见官方题解https://leetcode.com/articles/maximum-gap/
//        对于range=(max-min)内的N个数，如果排序后的任意相邻两数差值相同，那么【这个差值一定是所有可能的最大差值中的最小值】，即同样的range和元素个数N，
//        其他的两数间隔得到的最大差值必然比这个差值大。
//        所以，可以利用这个最小的最大差值作为桶的大小bucketSize，那么在N个数均匀分布的情况下，所有数都会在bucket的最左侧，和右边的数（即下一个bucket最左侧）
//        相差一个bucketSize。
//        在range和N确定的情况下，最大bucketSize（即相对应的桶的个数bucketNum）也随之确定了。当N个数不是均匀分布的情况下，根据抽屉原理，必然会出现相邻两数间隔
//        比bucketSize还大的情况（甚至出现空桶），桶内相邻元素的间隔必然比bucketSize要小。因此，maximum gap只可能出现在相邻桶之间，具体来说是前一个非空桶的最大元素
//        和下一个非空桶的最小元素之间。所以，对于每个桶，只需要维护两个变量（3个信息），桶内最大值、最小值和桶是否为空。
//        bucketSize表示桶内最大能容纳多少个不同的数。事实上，bucketSize可以取[1:range/(N-1)]。
//        用Bucket Sort时，从定义上保证了至少有1个bucket。如果数组中有至少两个不同的数，那么一定至少有2个bucket。对于n=nums.length，k=num of buckets，一定有k<=n。
// 时间复杂度：O(n)
// 空间复杂度：O(k), k=num of buckets
// 犯错点：1.如果(max - min) < (N - 1)，那么要确保bucketSize至少为1

// 另一种写法：bucketSize = (max - min) / n + 1

class Solution {
    public int maximumGap(int[] nums) {
        if (nums.length < 2) return 0;
        
        /* determine the maximum difference */
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int n: nums) {
            min = Math.min(min, n);
            max = Math.max(max, n);
        }
        
        /* determine the bucket size and bucket numbers. */
        /* There must be at least 1 bucket. If there are two distinct elements, there must be at least 2 buckets. */
        //int bucketSize = (max - min) / (nums.length - 1);  // {Mistake 1: in case (max - min) < (nums.length - 1), bucketSize must be greater than or equal to 1}
        int bucketSize = Math.max(1, (max - min) / (nums.length - 1));  // {Correction 1}
        int bucketNum = 1 + ((max - min) / bucketSize);  // this is unnecessary, because even buckets array is allocated with length N, it will not be used up
        int[][] buckets = new int[bucketNum][2];
        
        /* mark empty bucket as max=buckets[i][0]=-1, min=buckets[i][1]=Integer.MAX_VALUE */
        for (int i = 0; i < bucketNum; i++) {  // bucketNum can also be nums.length, then space complexity will be O(n)
            buckets[i][0] = -1;
            buckets[i][1] = Integer.MAX_VALUE;
        }
        
        /* put all n in nums array into buckets, and update buckets[i][0] and buckets[i][1] */
        for (int n: nums) {
            int bucketIndex = (n - min) / bucketSize;  // determine which bucket it falls into
            buckets[bucketIndex][0] = Math.max(buckets[bucketIndex][0], n);
            buckets[bucketIndex][1] = Math.min(buckets[bucketIndex][1], n);
        }
        
        int maxDiff = 0;
        int preMax = buckets[0][0];  // it is certain that there must be at least 1 bucket
        for (int i = 1; i < bucketNum; i++) {
            if (buckets[i][0] == -1) continue;  // skip empty bucket
            maxDiff = Math.max(maxDiff, buckets[i][1] - preMax);  // get the maximum difference between two non-empty buckets
            preMax = buckets[i][0];
        }
        
        return maxDiff;
    }
}