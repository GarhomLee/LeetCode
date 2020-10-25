https://leetcode.com/problems/path-with-minimum-effort/

idea: DisjointSet + minHeap
    -Consider the dist between two adjacent cells as the cost of the edge in a graph.
    -Find the first minimum dist (edge) with which the start and the target and be connected.
time complexity: O(rowLen*colLen*log(rowLen*colLen))
space complexity: O(rowLen*colLen)

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
    
    public int minimumEffortPath(int[][] heights) {
        int rowLen = heights.length, colLen = rowLen == 0 ? 0 : heights[0].length;   
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        for (int row = 0; row < rowLen; row++) {
            for (int col = 0; col < colLen; col++) {
                if (col + 1 < colLen) {
                    int curr = row*colLen + col;
                    int next = row*colLen + (col+1);
                    int dist = Math.abs(heights[row][col] - heights[row][col+1]);
                    pq.offer(new int[]{curr, next, dist});
                }
                if (row + 1 < rowLen) {
                    int curr = row*colLen + col;
                    int next = (row+1)*colLen + col;
                    int dist = Math.abs(heights[row][col] - heights[row+1][col]);
                    pq.offer(new int[]{curr, next, dist});
                }
            }
        }
        
        DisjointSet ds = new DisjointSet(rowLen*colLen);
        int target = rowLen*colLen - 1;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int cell1 = pair[0], cell2 = pair[1], dist = pair[2];
            ds.connect(cell1, cell2);
            if (ds.isConnected(0, target)) {
                return dist;
            }
        }
        
        return 0;   // when there is only one cell in the matrix
    }
}