https://leetcode.com/problems/largest-component-size-by-common-factor/

Brute Force:对任意两个数，求gcd，做union find。
时间复杂度：O(n^2 * log n)
空间复杂度：O(n)
问题：时间复杂度太高


优化：视频讲解https://www.youtube.com/watch?v=GTX0kw63Tn0
    对每个数求factor（时间为O(sqrt(max))），将factor相同的数做union find。
    然后再次遍历A数组，统计出现最多的root的出现次数。
时间复杂度：O(n * sqrt(max)), max=maximum element in A
空间复杂度：O(max), max=maximum element in A

class Solution {
    class UnionFind {
        int[] parent;
        int[] size;
        int maxSize;
    
        public UnionFind(int n) {
            parent = new int[n];
            size = new int[n];
            maxSize = 1;
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
            maxSize = Math.max(maxSize, size[rj]);
        }
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
    }
    
    public int largestComponentSize(int[] A) {
        int len = A.length, max = 0;
        for (int num : A) {
            max = Math.max(max, num);
        }
        
        UnionFind uf = new UnionFind(max + 1);
        for (int num : A) {
            for (int i = 1; i <= Math.sqrt(num); i++) {
                if (num % i == 0) {
                    if (i != 1) {
                        uf.connect(i, num);
                    }
                    if (num / i != 1) {
                        uf.connect(num / i, num);
                    }
                }
            }
            
        }
        
        Map<Integer, Integer> map = new HashMap<>();
        int maxCount = 0;
        for (int num : A) {
            int root = uf.find(num);
            map.put(root, map.getOrDefault(root, 0) + 1);
            maxCount = Math.max(maxCount, map.get(root));
        }
        
        return maxCount;
    }
}