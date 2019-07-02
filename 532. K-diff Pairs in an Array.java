https://leetcode.com/problems/k-diff-pairs-in-an-array/

// 思路：HashMap
//      HashMap中key为nums数组出现的数字，value为出现的次数（不严格）。实际上，统计次数只是为了应对k==0时的
//      重复情况（统一元素出现2次以上），对于k > 0的情况不需要用到次数统计。
//      遍历数组，对于当前元素n可能有三种情况：
//      1）n第一次出现，那么先找是否存在n + k和n - k，再将n和对应的次数1放进HashMap。按这个顺序，就不需要考虑k==0的情况。
//      2）n已经出现过，那么如果k==0，且n只出现过一次，这也是合理的pair，res++，同时更新n的次数
//      3）其他情况，不用考虑，直接跳过
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.边界条件：因为k为绝对差值，所以k<0是不合理的，返回0

class Solution {
    public int findPairs(int[] nums, int k) {
        // {Mistake 1}
        /* corner case */
        if (k < 0) return 0;  // {Correction 1}
        
        int res = 0;
        Map<Integer, Integer> map = new HashMap();
        for (int n: nums) {
            if (!map.containsKey(n)) {
                if (map.containsKey(n + k)) res++;
                if (map.containsKey(n - k)) res++;
                map.put(n, 1);
            } else if (k == 0 && map.get(n) == 1) {
                res++;
                map.put(n, map.get(n) + 1);
            } 
        }
        return res;
    }
}


// 解法二：2 HashSets
//        对k==0的情况单独讨论，一个HashSet存只出现一次的元素，另一个存出现两次以上的元素。
//        k>0的情况，只需要一个HashSet。