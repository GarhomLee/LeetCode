https://leetcode.com/problems/longest-harmonious-subsequence/

// 思路：HashMap
//      key为出现过的数字，value为该数字在nums数组中出现的次数。

// 写法一：Two-pass
//      第一次遍历，将HashMap建立起来。
//      第二次遍历，从所有的{n,n+1}配对中找到最大的个数。

class Solution {
    public int findLHS(int[] nums) {
        /* corner case */
        if (nums == null || nums.length == 0) return 0;
        /* construct key-value pairing */
        Map<Integer, Integer> map = new HashMap();
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        /* iterate HashMap again to find the longest harmonious subsequence */
        int maxLen = 0;
        for (int n: map.keySet()) {
            if (map.containsKey(n + 1)) {
                maxLen = Math.max(maxLen, map.get(n) + map.get(n + 1));
            }
        }
        
        return maxLen;
    }
}

// 写法二：One-pass
//        只需要遍历一次，一边更新HashMap信息，一边利用{n-1,n}和{n,n+1}的配对信息来更新maxLen。

class Solution {
    public int findLHS(int[] nums) {
        /* corner case */
        if (nums == null || nums.length == 0) return 0;

        Map<Integer, Integer> map = new HashMap();
        int maxLen = 0;
        for (int n: nums) {
            map.put(n, map.getOrDefault(n, 0) + 1);
            /* find the longest length in {n-1,n} pair or {n,n+1} pair */
            if (map.containsKey(n - 1)) {
                maxLen = Math.max(maxLen, map.get(n) + map.get(n - 1));
            }
            if (map.containsKey(n + 1)) {
                maxLen = Math.max(maxLen, map.get(n) + map.get(n + 1));
            }
        }
        
        return maxLen;
    }
}