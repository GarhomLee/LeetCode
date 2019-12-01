https://leetcode.com/problems/delete-tree-nodes/

// 思路：DFS
//         预处理：维护全局变量n，表示删除节点后所剩的节点个数。
//             同时，将parent数组转化为children数组，能根据index得到对应的相邻子节点。
//         递归函数定义：int[] dfs(int currNode, List<Integer>[] children, int[] value)，表示根据children
//             数组和value数组，遍历以currNode为根节点的子树，得到该子树的节点数值的和，以及节点总个数。
//         终止条件：无特殊终止条件。如果当前节点currNode为叶子节点，即children数组为空，同样可以判断是否需要删除该子树。
//         递归过程：维护临时变量：sum，表示当前节点currNode为根的子树的和；nodeNum，表示当前节点currNode为根的子树的
//             节点总数。
//             遍历当前节点的所有子节点children[currNode]，调用递归函数更新sum和currNode。
//             如果sum为0，那么包括currNode在内的所有节点都可以删除，因此n更新为减去nodeNum，同时nodeNum重置为0。
//             否则，不满足加和为0，都不能删除，不需要更新。
//             返回数组{sum, nodeNum}。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    int n = -1;
    
    private int[] dfs(int currNode, List<Integer>[] children, int[] value) {
        int sum = value[currNode], nodeNum = 1;
        for (int child : children[currNode]) {
            int[] pair = dfs(child, children, value);
            sum += pair[0];
            nodeNum += pair[1];
        }
        
        if (sum  == 0) {
            n -= nodeNum;
            nodeNum = 0;
        }
        
        return new int[]{sum, nodeNum};
    }
    
    public int deleteTreeNodes(int nodes, int[] parent, int[] value) {
        n = nodes;
        List<Integer>[] children = new List[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }
        for (int i = 1; i < parent.length; i++) {
            children[parent[i]].add(i);
        }
        dfs(0, children, value);
        
        return n;
    }
}