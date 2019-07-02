https://leetcode.com/problems/subarray-sum-equals-k/

// 解法一：Brute Force
//        对于nums数组每一个元素，遍历它后面的所有元素并求和，看是否等于k
// 时间复杂度：O(n^2)
// 空间复杂度：O(1)
// 出现的问题：时间复杂度太高


// 解法二：Memoization
//        利用sums数组记录[0:i)的prefix sum，然后遍历，对于每个prefix sum，遍历它后面的所有sum并求差，看是否等于k
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
// 出现的问题：时间复杂度太高


// 解法三：HashMap
//        HashMap中的key为所有出现的[0:i)的prefix sum（初始状态下，没有访问任何一个元素，prefix sum为0），value
//        为对应prefix sum出现的次数。
//        遍历nums数组，不断更新变量sum。查找HashMap中是否存在sum - k，因为sum - k是[0:j)的prefix sum，则k是
//        [j:i)的prefix sum。如果数组中出现过M次sum - k，那么也必然出现过M次k，因此结果res增加M。
//        更新sum对应的次数。
// 注意：HashMap初始化为带有key:0, value:1这个元素。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int subarraySum(int[] nums, int k) {
        int res = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap();
        map.put(0, 1);  // initialize
        for (int n: nums) {
            sum += n;
            if (map.containsKey(sum - k)) {
                res += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
}