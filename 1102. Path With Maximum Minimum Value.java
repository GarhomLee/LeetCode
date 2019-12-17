https://leetcode.com/problems/path-with-maximum-minimum-value/

解法一：BFS with maxHeap

时间复杂度：O(rowLen * colLen * log(rowLen * colLen))
空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    public int maximumMinimumPath(int[][] A) {
        int rowLen = A.length, colLen = rowLen == 0 ? 0 : A[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        boolean[][] visited = new boolean[rowLen][colLen];
        pq.offer(new int[]{0, 0, A[0][0]});
        visited[0][0] = true;
        int minimax = A[0][0];
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int row = curr[0], col = curr[1], value = curr[2];
            
            minimax = Math.min(minimax, value);
            if (row == rowLen - 1 && col == colLen - 1) {
                return minimax;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen || visited[nextRow][nextCol]) continue;
                
                visited[nextRow][nextCol] = true;
                pq.offer(new int[]{nextRow, nextCol, Math.min(value, A[nextRow][nextCol])});
            }
        }
        
        return -1;
    }
}


// 解法二：Union Find
//         将所有元素从大到小排序，每次从PriorityQueue取出元素后将它和周围被搜索过的元素进行union。
//         如果发现{0,0}和{row-1,col-1}两个元素已经连通，那么这时候得到的元素即为所求。
// 时间复杂度：O(rowLen * colLen * log(rowLen * colLen))
// 空间复杂度：O(rowLen * colLen)

class Solution {
    int[] dir = new int[]{-1, 0, 1, 0, -1};
    
    class UnionFind {
        int[] parent;
        int[] size;
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        
        public void connect(int i, int j) {
            int ri = find(i), rj = find(j);
            if (ri == rj) {
                return;
            }
            
            if (size[ri] > size[rj]) {
                connect(rj, ri);
            }
            parent[ri] = rj;
            size[rj] += size[ri];
        }
        
        public int find(int i) {
            while (parent[i] != i) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            
            return i;
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    } 
    
    public int maximumMinimumPath(int[][] A) {
        int rowLen = A.length, colLen = rowLen == 0 ? 0 : A[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[2] - a[2]);
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                pq.offer(new int[]{row, col, A[row][col]});
            }
        }
        
        UnionFind uf = new UnionFind(rowLen * colLen);
        Set<Integer> set = new HashSet<>();
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int row = curr[0], col = curr[1], value = curr[2];
            int index = row * colLen + col;
            set.add(index);
            for (int i = 0; i < 4; i++) {
                int nextRow = row + dir[i], nextCol = col + dir[i + 1];
                int nextIndex = nextRow * colLen + nextCol;
                if (nextRow < 0 || nextRow >= rowLen || nextCol < 0 || nextCol >= colLen || !set.contains(nextIndex)) continue;
                
                uf.connect(index, nextIndex);
            }
            
            if (uf.isConnected(0, rowLen * colLen - 1)) {
                return value;
            }
        }
        
        return -1;
    }
}