https://leetcode.com/problems/swim-in-rising-water/

Solution 1: Binary Search + BFS/DFS

time complexity: O(n^2 log(n^2))=O(n^2 *2log(n))=O(n^2 log(n))
space complexity: O(n^2)

class Solution {
    int[] dir = {-1, 0, 1, 0, -1};
    
    private boolean canReach(int[][] grid, int t) {
        int rowlen = grid.length, collen = rowlen == 0 ? 0 : grid[0].length;
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rowlen][collen];
        if (grid[0][0] <= t) {
            queue.offer(new int[]{0, 0});
            visited[0][0] = true;
        }
        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            if (curr[0] == rowlen - 1 && curr[1] == collen - 1) {
                return true;
            }
            
            for (int i = 0; i < 4; i++) {
                int nextrow = curr[0] + dir[i], nextcol = curr[1] + dir[i + 1];
                if (nextrow >= 0 && nextrow < rowlen && nextcol >= 0 && nextcol < collen
                   && !visited[nextrow][nextcol] 
                    && grid[nextrow][nextcol] <= t) {
                    queue.offer(new int[]{nextrow, nextcol});
                    visited[nextrow][nextcol] = true;
                }
            }
        }
        
        return false;
    }
    
    public int swimInWater(int[][] grid) {
        int rowlen = grid.length, collen = rowlen == 0 ? 0 : grid[0].length;
        int low = 0, high = rowlen * collen - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (canReach(grid, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        
        return low;
    }
}

Solution 2: Min Heap + Union Find

time complexity: O(n^2 log(n^2))=O(n^2 *2log(n))=O(n^2 log(n))
space complexity: O(n^2)

class Solution {
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
            int rooti = find(i), rootj = find(j);
            if (size[rooti] > size[rootj]) {
                connect(rootj, rooti);
                return;
            }
            
            parent[rooti] = rootj;
            size[rootj] += size[rooti];
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
    
    int[] dir = {-1, 0, 1, 0, -1};
    
    public int swimInWater(int[][] grid) {
        int rowlen = grid.length, collen = rowlen == 0 ? 0 : grid[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        
        // sort all cells by the time value
        for (int row = 0; row < rowlen; row++) {
            for (int col = 0; col < collen; col++) {
                int index = row * collen + col;
                pq.offer(new int[]{grid[row][col], index});
            }
        }
        
        UnionFind uf = new UnionFind(rowlen * collen);
        int goal = rowlen * collen - 1;
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int time = curr[0], row = curr[1] / collen, col = curr[1] % collen;
            for (int i = 0; i < 4; i++) {
                int nextrow = row + dir[i], nextcol = col + dir[i + 1];
                int nextindex = nextrow * collen + nextcol;
                if (nextrow >= 0 && nextrow < rowlen && nextcol >= 0 && nextcol < collen 
                    && grid[row][col] >= grid[nextrow][nextcol]) {
                    uf.connect(curr[1], nextindex);
                }
            }
            
            if (uf.isConnected(0, goal)) {
                return time;
            }
        }
        
        return -1;
    }
}