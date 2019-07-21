https://leetcode.com/problems/beautiful-arrangement/

// 解法一：DFS (Recursion) + Backtracking
//     goal：pos == N + 1，所有位置都放置了合法元素，res++
//     choices：[1:N]所有数
//     constraints：1）每个数只能放一次，因此要先判断是否已经用过了，用过的元素要跳过
//                 2）如果i % pos != 0 && pos % i != 0，那么i不能放在位置pos，跳过
// 时间复杂度：O(k), k=number of valid permutations
// 空间复杂度：递归深度O(n)
// 犯错点：1.终止条件错误：pos==N时，还不能终止，因为pos位置还要放元素。应该在pos == N + 1时终止，这时才表示所有位置都放了元素。

class Solution {
    int res = 0;
    
    public int countArrangement(int N) {
        boolean[] used = new boolean[N + 1];
        dfs(N, 1, used);
        return res;
    }
    
    private void dfs(int N, int pos, boolean[] used) {
        /* GOAL */
        //if (pos == N)  // {Mistake 1}
        if (pos == N + 1) {  // {Correction 1}
            res++;
            return;
        }
        
        /* CHOICES */
        for (int i = 1; i <= N; i++) {
            /* CONSTRAINTS */
            if (used[i] || (i % pos != 0 && pos % i != 0)) continue;

            used[i] = true;
            dfs(N, pos + 1, used);
            used[i] = false;  // reset
        }
    }
}


解法二：swap