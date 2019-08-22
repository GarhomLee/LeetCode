https://leetcode.com/problems/redundant-connection-ii/

// 对比：684. Redundant Connection，本题为有向图

// 思路：Union Find。视频讲解：https://www.youtube.com/watch?v=lnmJT5b4NlM
//         对于有向图中多出来的第n条边，有3种可能情况：
//         1）成环，但所有节点都只有1个parent，和684题情况类似
//         2）不成环，但有一个节点有2个parent
//         3）成环，且有一个节点有2个parent
//         因此，需要在遍历的过程中需要检测出属于哪一种情况。
//         step0: 维护变量：root数组，表示每个节点的root，需要初始化为节点自身；parent数组，表示指向当前节点的
//             直接parent，初始值为0表示还没扫描到；size数组，表示每个component的元素个数，需要初始化为1；
//             candidate1和candidate2，表示如果出现的某个节点有2个parent的情况，两条parent边必然有一条要被
//             删除，需要【显式初始化为null】。
//         step1: 第一遍扫描edges数组，目的是初始化root和size数组，同时利用parent数组记录指向当前节点的直接parent，
//             来【检测是否出现某个节点有2个parent的情况】。如果某个节点的parent不为0，就意味着出现了这个情况，那么
//             将两条parent边存在candidate1和candidate2，【同时删除当前的边（赋值为-1），使得edges的边的个数变为
//             n-1】。
//         step2: 再次扫描edges数组，目的是将有edge连接的节点用union find【检测是否成环】。
//             如果检测出成环，那么要删除掉环的某一条边。如果candidate1不为null，即还存在某个节点有2个parent，属于
//             情况3），那么一定是删除candidate1；否则candidate1为null，属于情况1），那么要删除当前加入的边。
//             在扫描的过程中，连接edge的两个节点。
//         step3: 如果扫描完edges数组，都没有检测到成环，那么就是情况2），返回第一次遍历时删掉的candidate2。
// 时间复杂度：amortized O(n)
// 空间复杂度：O(n)
// 犯错点：1.初始化错误：即使candidate1, candidate2初始化为null，也要显式地声明。
//         2.细节错误：不能直接将edge赋值到candidate2，因为后面改变edge地值的时候，由于candidate2也有同样的reference，
//             candidate2的值也会改变。因此，candidate2要new一个新的int[]。

class Solution {
    int[] root;
    int[] parent;
    int[] size;
    public int[] findRedundantDirectedConnection(int[][] edges) {
        /* initialization */
        int n = edges.length;
        root = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        //int[] candidate1, candidate2;  // {Mistake 1}
        int[] candidate1 = null, candidate2 = null;  // {Correction 1}
        /* first-pass: record parent of each vertex, and detect if there is a node with more than one parent */
        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];
            /* initialize root array and size array while scanning */
            root[from] = from;
            root[to] = to;
            size[from] = 1;
            size[to] = 1;
            /* vertex with more than one parent is detected */
            if (parent[to] > 0) {
                /* one of these two parent edges will be the final result */
                candidate1 = new int[]{parent[to], to};
                //candidate2 = new int[]{from, to};  // {Mistake 2}
                candidate2 = new int[]{from, to};  // {Correction 2}
                /* trick: delete this candidate edge */
                edge[0] = -1;
                edge[1] = -1;
            }
            /* record the parent of to as from */
            parent[to] = from;
        }
        /* second-pass: detect cycle by union find */
        for (int[] edge: edges) {
            int from = edge[0], to = edge[1];
            if (from < 0 && to < 0) continue;  // skip the deleted edge
            
            int r1 = find(from), r2 = find(to);
            /* detect cycle */
            if (r1 == r2) {
                return candidate1 == null ? edge : candidate1;  // check if there is a node with more than one parent
            }
            /* no cycle */
            connect(from, to);
        }
        
        return candidate2;  // candidate1 is not the result, thus candidate2 is the final result
    }
    /** find the root of vertex i, and perform path compression while finding */
    private int find(int i) {
        while (i != root[i]) {
            root[i] = root[root[i]];
            i = root[i];
        }
        return i;
    }
    /** connect two vertices i and j into a same union set */
    private void connect(int i, int j) {
        int r1 = find(i), r2 = find(j);
        if (r1 == r2) {
            return;
        }
        
        if (size[r1] > size[r2]) {
            root[r2] = r1;
            size[r1] += size[r2];
        } else {
            root[r1] = r2;
            size[r2] += size[r1];
        }
    }
}