https://leetcode.com/problems/minimum-height-trees/

// 思路：对于Graph从外到内的BFS。
//      重点：维护变量degree。根据题意可知，最后只会剩下一个或两个节点。如果剩下一个节点，那么degree为0；如果剩下
//           两个节点，每个节点有1点degree，所以degree为2。
//      构建Graph，利用array of List实现（不用Set是因为后面要找到和leaf node相邻的元素，用List可以直接用get(0)，
//      而Set没有办法很方便地得到）。由于是undirected graph，所以两个端点v和w对应的两条边v->w和w->v都要加进去。
//      每加入一条边，degree增加2，因为每个节点贡献1点degree。
//      遍历graph中所有节点，找到leaf node（即只有1个相邻元素），加入Queue，开始BFS。
//      对于当前的leaf node，记为curr，与之相连的节点记为next。如果要把curr删除，只需要找到next节点，删除next邻居
//      中的curr即可实现。如果删除curr后，next只剩1个相邻元素，那么next就是新的leaf node，加入Queue。
//      每删除一个元素，degree减少2，直到总的degree <= 2为止。这时Queue中剩下的元素就是可能的MHT的root。
// 犯错点：1.边界条件：如果只有1个node，那么直接返回res，里面的元素只有一个0.

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> res = new ArrayList();
        // {Mistake 1: test case n = 1, edges = []; should return [0]}
        /* corner case */
        if (edges.length == 0) {
            res.add(0);
            return res;
        }  // {Correction 1}
        
        /* graph construction */
        List<Integer>[] graph = new List[n];
        int degree = 0;
        for (int i = 0; i < edges.length; i++) {
            int vertex1 = edges[i][0], vertex2 = edges[i][1];
            if (graph[vertex1] == null) graph[vertex1] = new ArrayList();
            graph[vertex1].add(vertex2);
            if (graph[vertex2] == null) graph[vertex2] = new ArrayList();
            graph[vertex2].add(vertex1);
            degree += 2;  // each node on this edge contributes to 1 degree
        }
        
        /* use Queue to perform BFS */
        Queue<Integer> queue = new LinkedList();
        for (int vertex = 0; vertex < n; vertex++) {
            if (graph[vertex].size() == 1) {  // initialize the Queue with leaf nodes
                queue.offer(vertex);
            }
        }
        
        /* remove all leaf nodes from outside to inside until there are 1 or 2 nodes left, whose total degree should be 0 or 2 */
        while (degree > 2) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                int next = graph[curr].get(0);  // find the adjacent node
                graph[next].remove((Integer) curr);  // remove curr from the neighbor list of next
                degree -= 2;
                if (graph[next].size() == 1) queue.offer(next);  // there is only 1 adjacent node of next, which means next is now a leaf node
            }
        }
        
        /* the nodes left in the Queue should be the final result */
        while (!queue.isEmpty()) {
            res.add(queue.poll());
        }
        return res;
    }
}