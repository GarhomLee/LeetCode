https://leetcode.com/problems/number-of-islands-ii/

思路：Union Find，稍微修改了模版

时间复杂度：O(rowLen * colLen + L), L=positions.length
空间复杂度：O(rowLen * colLen)
犯错点：1.判断条件错误：如果仅靠判断0 <= nextIndex <= m*n,那么可能会出现nextCol >= colLen也能满足
            nextIndex的范围要求，因此正确做法是判断0 <= nextRow < m同时0 <= nextCol < n。
        2.边界条件错误：parent[i] < 0时，这个点并未初始化，因此find()直接返回parent[i]，否则会出现
            IndexRangeError
        3.细节错误：path compression用while循环而不是if判断（花了2个小时debug！）

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        UnionFind uf = new UnionFind(m * n);
        List<Integer> res = new ArrayList<>();
        
        for (int[] pos : positions) {
            int index = pos[0] * n + pos[1];  // get the current index
            
            uf.setParent(index);  // set this position as a new land
            
            for (int i = 0; i < 4; i++) {
                int nextRow = pos[0] + dir[i], nextCol = pos[1] + dir[i + 1];
                int nextIndex = nextRow * n + nextCol;

                //if (nextIndex >= 0 && nextIndex < m * n && uf.find(nextIndex) >= 0) // {Mistake 1}
                if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n && uf.find(nextIndex) >= 0) {  // {Correction 1}
                    // the neighbor cell is a valid land
                    uf.connect(index, nextIndex);
                }
            }

            res.add(uf.count);
        }
        
        return res;
    }
    
    class UnionFind {
        int[] parent;
        int[] size;
        int count;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            count = 0;  // record the count of connected lands
            Arrays.fill(parent, -1);  // initialize as -1, meaning it is not a land yet
        }
        
        public void setParent(int i) {
            if (parent[i] >= 0) return;
            parent[i] = i;
            size[i] = 1;
            count++;
        }
        public int find(int i) {
            // {Mistake 2}
            // if this element is not set yet
            if (parent[i] < 0) {
                return parent[i];
            }  // {Correction 2}
            
            //if (i != parent[i]) {Mistake 3}
            while (i != parent[i]) {  // {Correction 3}
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
        
        public void connect(int i, int j) {
            int p1 = find(i), p2 = find(j);
            if (p1 == p2) return;
            
            if (size[i] > size[j]) {
                connect(j, i);
                return;
            }
            
            parent[p1] = p2;
            size[p2] += size[p1];
            count--;
        }
    }
}


二刷：用普通的DisjointSet模板
class Solution {
    class DisjointSet {
        int[] parent;
        int[] size;
        
        public DisjointSet(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) return;
            if (size[ri] > size[rj]) {
                connect(rj, ri);
                return;
            }
            
            parent[ri] = rj;
            size[rj] += size[ri];
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }   
            return i;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        int count = 0;
        List<Integer> res = new ArrayList<>();
        
        int[] dir = {-1, 0, 1, 0, -1};
        DisjointSet ds = new DisjointSet(m*n);
        int[][] grid = new int[m][n];
        for (int[] pos : positions) {
            int row = pos[0], col = pos[1];
            int curr = row*n + col;
            if (grid[row][col] == 0) {
                // update only when the position is not visited yet
                count += 1;
                grid[row][col] = 1;
                for (int i = 0; i + 1 < dir.length; i++) {
                    int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                    int next = nextRow*n + nextCol;
                    if (nextRow < 0 || nextRow >= m || nextCol < 0 || nextCol >= n 
                        || grid[nextRow][nextCol] == 0) continue;

                    if (!ds.isConnected(curr, next)) {
                        count -= 1;
                    }
                    ds.connect(curr, next);
                }
            }
            
            res.add(count);
        }
        
        return res;
    }
}