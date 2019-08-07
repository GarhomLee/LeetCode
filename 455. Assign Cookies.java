https://leetcode.com/problems/assign-cookies/

// 思路：Greedy + Sort
//         将小孩和饼干先排序，依次把能满足第i个小孩的饼干给小孩，直到搜索完所有饼干。
// 时间复杂度：O(n log n)
// 空间复杂度：O(1)

class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int res = 0;
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (g[i] <= s[j]) {
                res++;
                i++;
            }
            j++;
        }
        return res;
    }
}