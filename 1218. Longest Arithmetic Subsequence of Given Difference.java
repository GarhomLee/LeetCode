https://leetcode.com/problems/longest-arithmetic-subsequence-of-given-difference/

// 思路：Hash Table + Dynamic Programming
//         状态函数：利用HashMap，key为出现过的arr数组元素，value为以该元素为结尾的、所处的间隔为difference的
//             最长subsequence的长度。
//         初始值：对于新出现的元素，所处subsequence的初始长度为1。
//         状态转移方程：对于每个元素，利用HashMap查找subsequence的前一个元素n - difference，如果存在这前一个元素，
//             那么该subsequence的长度+1，且将当前元素和这个长度放入HashMap中。
//             同时，利用这个长度更新全局最大值max。
// 时间复杂度：O(n)
// 空间的复杂度：O(n)

class Solution {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int n: arr) {
            map.put(n, map.getOrDefault(n - difference, 0) + 1);
            max = Math.max(max, map.get(n));
        }
        
        return max;
    }
}