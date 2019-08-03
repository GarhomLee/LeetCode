https://leetcode.com/problems/lexicographical-numbers/

// 思路：DFS (Recursion)
//         递归函数定义：void dfs(int n, int curr)，表示搜索得到curr为前缀的不超过n的lexicographical list
//         终止条件：curr > n，前缀已经比n大，停止搜索
//         递归过程：首先，当前前缀curr符合题意，加入List。
//                 分别将[0:9]追加到curr后，即curr * 10 + i，调用递归函数继续向下一位搜索。
//                 优化：如果curr * 10 + i > n，就可以停止搜索了
// 时间复杂度：O(n)
// 空间复杂度：O(n)（递归深度O(log n)？）

class Solution {
    List<Integer> list = new ArrayList<>();

    public List<Integer> lexicalOrder(int n) {
        if (n <= 0) return list;
        for (int i = 1; i <= 9; i++) {
            dfs(n, i);
        }
        
        return list;
    }
    
    private void dfs(int n, int curr) {
        if (curr > n) {
            return;
        }
        list.add(curr);
        
        for (int i = 0; i <= 9; i++) {
            if (curr * 10 + i > n) {
                break;
            }
            
            dfs(n, curr * 10 + i);
        }
    }
}