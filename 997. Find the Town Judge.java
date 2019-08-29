https://leetcode.com/problems/find-the-town-judge/

// 思路：Directed Graph求in degree和out degree的问题，据说类似lc 277。参考：https://leetcode.com/problems/find-the-town-judge/discuss/242938/JavaC%2B%2BPython-Directed-Graph
//         根据题意，找town judge可以转化为是否能在N个节点中找到某一个节点，满足in degree为N-1，out degree为0。
//         由于in degree最大为1，out degree最大为0，因此可以直接用它们差值为N-1来判断。
//         维护degree数组，其中degree[i][0]表示第i个节点的out degree，degree[i][1]表示第i个节点的in degree。
//         遍历trust数组，更新所有元素的degree。再次遍历degree数组，搜索是否存在in degree - out degree ==  N - 1
//         的节点。
// 时间复杂度：O(E), E=num of edges
// 空间复杂度：O(V), V=num of vertices

class Solution {
    public int findJudge(int N, int[][] trust) {
        int[][] degree = new int[N + 1][2];  // degree[i][0] is out_degree, degree[i][1] is in_degree
        for (int[] edge: trust) {
            int from = edge[0], to = edge[1];
            degree[from][0]++;
            degree[to][1]++;
        }
        
        for (int i = 1; i <= N; i++) {
            if (degree[i][1] - degree[i][0] == N - 1) {
                return i;
            }
        }
        
        return -1;
    }
}