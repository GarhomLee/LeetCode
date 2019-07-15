https://leetcode.com/problems/longest-well-performing-interval/

// 思路：HashMap，利用prefix sum的性质，类似525. Contiguous Array。
//      题目可以转化为在只有1和-1的数组中包含最多1和-1、且1要多于-1的子数组的最大长度。
//      HashMap中，key为所有可能的前缀和sum（即hours[0:i]的和），value为这个sum第一次出现的位置。
//      遍历hours数组，将hours[i]变为1和-1，然后更新sum，如果sum第一次出现，更新Map。
//      此时sum有两种可能情况：
//      1）sum > 0，即hours[0:i]中1比-1多，这时的长度一定是当前能得到的最大长度，因此maxLen = i + 1
//      2）sum <= 0，那么要找到之前的某个前缀和sum'，使得sum-sum'之间的范围内尽可能包含多一些1和-1，
//         且1要多于-1。由于hours[0:i]中-1比1多（或一样多），所以能满足“尽可能包含多一些1和-1”和
//         “1要多于-1”这两个条件的sum-sum'必定为1，即sum'=sum-1。因此，从Map中调取sum-1出现的位置j，
//         因为Map存的是第一次出现的位置，因此i-j一定是最大的范围。
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 优化：因为只有当sum <= 0时才用到Map，因此能用到的sum-1一定比0小，所以只需要在sum <= 0时对第一次出现的sum更新Map。

class Solution {
    public int longestWPI(int[] hours) {
        if (hours.length == 0) return 0;
        int maxLen = 0;
        Map<Integer, Integer> map = new HashMap();
        int sum = 0;  // sum[i] indicates the sum of hours[0:i] after transformation
		
        for (int i = 0; i < hours.length; i++) {
            sum += hours[i] > 8 ? 1 : -1;  // transform each hour to 1 or -1
            if (!map.containsKey(sum)) {
                map.put(sum, i);  // record where the sum appears for the first time
            }
			
            if (sum > 0) {  // in hours[0:i], more 1s than -1s
                maxLen = i + 1;
            } else if (map.containsKey(sum - 1)) {  // get the index j where sum of hours[0:j] is sum - 1, so that sum of hours[j+1:i] is 1
                maxLen = Math.max(maxLen, i - map.get(sum - 1));
            }            
            
        }
        
        return maxLen;
    }
}