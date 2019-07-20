https://leetcode.com/problems/matchsticks-to-square/

// Intuition：DFS (Recursion) + Backtracking + TreeMap，为了从小到大遍历元素，同时还要记录元素个数
// 缺点：每次都要从头开始遍历，造成很多combination里的重复判断，同时每次取元素和重置元素个数都是O(log n)


// 优化：DFS (Recursion) + Backtracking + Sort + 辅助数组。视频讲解https://www.youtube.com/watch?v=Z8cz4Wb5P2g
//         首先，遍历nums数组求和。如果和为0或不能被4整除，那么根据题意，直接可以返回false。
//         将nums数组排序，递归求解。递归函数dfs(int[] nums, int start, int len, int curr, int sideNum, boolean[] used)
//         表示对于nums数组中的数，某些已经被用过记录在used数组里，从start位置开始，当前找到了sideNum条边，当前边拼到了curr长度，
//         是否能从剩下的数里找到能拼够4条相等的边且所有的数全部都用且只用一次。
//         实际上，本题在常规的backtracking基础上【只是多了一次判断和重置】。
//         goal：1）最终目标：sideNum==3，表示找到了3条边还剩1条边。由于题目的要求前3条边的和必然为总和sum的3/4，因此最后一条边
//                     一定可以凑成总和的1/4，且全部用完，因此直接返回true。
//                 2）阶段目标：curr==len，表示当前拼出的边长和所需边长相等，因此将start位置重置为0，当前边长curr重置为0，sideNum + 1，
//                     在剩下的数中继续拼下一条边
//         choices：nums数组中从start位置开始到最后的所有数
//         constraints：1）跳过已经用过的数（used[i]为true）
//                     2）跳过重复搜索。这里的重复搜索指在搜索失败的元素上重复，即使是相同的相邻元素nums[i]==nums[i-1]，也要看
//                         nums[i-1]是否已经被使用过。只有没被使用过的相同元素nums[i-1]才算是重复搜索
//                     3）curr + nums[i] > len，由于nums数组排好序，因此nums[i:length)都不可能有合法结果，因此直接break
        
class Solution {
    public boolean makesquare(int[] nums) {
        /* base case */
        if (nums.length < 4) return false;
        
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        if (sum == 0 || sum % 4 != 0) return false;
        
        Arrays.sort(nums);
        return dfs(nums, 0, sum / 4, 0, 0, new boolean[nums.length]);
    }
    
    private boolean dfs(int[] nums, int start, int len, int curr, int sideNum, boolean[] used) {
        /* GOAL */
        if (sideNum == 3) return true;
        /* enter next level and reset start position and curr */
        if (curr == len) return dfs(nums, 0, len, 0, sideNum + 1, used);
        
        /* CHOICES */
        for (int i = start; i < nums.length; i++) {
            /* CONSTRAINTS */
            if (used[i]) continue;
            if (i > start && nums[i] == nums[i - 1] && !used[i - 1]) continue;  // skip searched but failed element
            
            if (curr + nums[i] > len) break;  // skip [i,nums.length) because there will never be a solution since nums array is sorted
            
            /* DFS */
            used[i] = true;
            if (dfs(nums, i + 1, len, curr + nums[i], sideNum, used)) return true;  // as long as one solution is found
            used[i] = false;  // reset
        }
        return false;
    }
}