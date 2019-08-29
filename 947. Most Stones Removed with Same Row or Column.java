https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

// 思路：转换成岛屿问题，使用Union Find
//         将处在同一行、列的石头按移除最多的方式来移除，结果会是相关联的这些石头中只剩下1个，不相关联的石头之间互不
//         影响。因此，可以将处在同一行、列的石头看成处于同一个disjoint union set的component，移除相关联的石头
//         后的最后结果就是DSU中的component个数。注意，在本题的DSU中行和列是属于【相同性质的元素】，假设input中石头
//         最大的行和列为i和j，那么DSU中将【最多包含i*j个元素】。
//         然而，并不是所有的行和列都会出现，只有和input中石头的坐标的行和列才会出现，而最大的行和列不在input中给定，
//         无法直接确定。因此，可以将DSU的大小初始化为数据规模最大值MAX*MAX，将石头坐标的行和列connect起来，行id范围
//         为[0:MAX)，列id范围为[MAX:2*MAX)。
//         由于DSU中的2 * MAX个元素不全部出现，因此需要再次遍历stones数组，将出现过的行和列在DSU中的root找出来，这样的
//         root的个数就代表了拿走相关联的最多石头数后不相关联的不互相影响的石头数，因此结果为总石头数N减去unique root的个数。
// 时间复杂度：O(N (log*N)) = O(N)
// 空间复杂度：O(N)
// 犯错点：1.审题错误：stones数组的行数rowLen（最大为1000）和列数colLen（一定为2）并非放置石头的二维空间的行列数。
//             因此，需要根据题目数据范围的最大值MAX=10000来将石头的二维坐标变为一维坐标。

class Solution {
    final int MAX = 10000;
    
    public int removeStones(int[][] stones) {
        //int rowLen = stones.length, colLen = rowLen == 0 ? 0 : stones[0].length;
        //UnionFind uf = new UnionFind(rowLen * colLen);  // {Mistake 1}
        UnionFind uf = new UnionFind(2 * MAX);  // {Correction 1}
        
        for (int[] stone: stones) {
            int i = stone[0], j = MAX + stone[1]; 
            uf.connect(i, j);
        }
        
        Set<Integer> set = new HashSet<>();
        for (int[] stone: stones) {
            int root = uf.find(stone[0]); 
            set.add(root);
        }
        
        return stones.length - set.size();
        
    }
    
    class UnionFind {
        int[] root;
        int[] size;
        
        public UnionFind(int n) {
            root = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int r1 = find(i), r2 = find(j);
            if (r1 > r2) {
                int temp = r1;
                r1 = r2;
                r2 = temp;
            }
            
            root[r1] = r2;
            size[r2] += size[r1];
        }
        
        public int find(int i) {
            while (root[i] != i) {
                root[i] = root[root[i]];
                i = root[i];
            }
            return i;
        }
    }
}