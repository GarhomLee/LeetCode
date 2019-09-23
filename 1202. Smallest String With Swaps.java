https://leetcode.com/problems/smallest-string-with-swaps/

// 思路：UnionFind + HashMap + minHeap，参考：https://leetcode.com/problems/smallest-string-with-swaps/discuss/387618/ChineseC%2B%2B-1202.
// 关键点：1.可交换的各个位置（节点）可以构成无向连通图
//         2.根据题意中的无限次交换，处于同一个无向连通图的节点的char可以进行任意排列，因此可以得到字典序最小的排列。
// 过程：step1:遍历所有配对，利用UnionFind将配对的两个位置（节点）连接。
//     step2:遍历String s的所有位置，得到每个位置在disjoint set中的root，并将root作为key，PriorityQueue为value，
//         放入HashMap中。将s[i]的char放入PriorityQueue，自动完成排序的过程。
//     step3:再次遍历String s的所有位置，得到每个位置在disjoint set中的root，从HashMap中将对应的PriorityQueue
//         中的顶部元素取出，填入StringBuilder的位置i。遍历完成后，StringBuilder中就是已经按字典序最小的排列。
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair: pairs) {
            uf.connect(pair.get(0), pair.get(1));
        }
        
        Map<Integer, PriorityQueue<Character>> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            int root = uf.find(i);
            map.putIfAbsent(root, new PriorityQueue<>());
            map.get(root).offer(s.charAt(i));
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            int root = uf.find(i);
            sb.append(map.get(root).poll());
        }
        
        return sb.toString();
    }
    
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
        
        public int find(int i) {
            while (i != parent[i]) {
                parent[i] = parent[parent[i]];
                i = parent[i];
            }
            return i;
        }
        
        public void connect(int i, int j) {
            int root1 = find(i), root2 = find(j);
            if (root1 == root2) {
                return;
            }
            if (size[root1] > size[root2]) {
                int temp = root1;
                root1 = root2;
                root2 = temp;
            }
            
            parent[root1] = root2;
            size[root2] += size[root1];
        }
        
        public boolean isConnected(int i, int j) {
            return find(i) == find(j);
        }
    }
}