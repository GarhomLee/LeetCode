https://leetcode.com/problems/path-with-maximum-probability/

idea: Graph (Dijkstra's shortest path algo) + PriorityQueue
time complexity: O(V + E log E)
space complexity: O(V)

class Solution {
    class Pair {
        double prob;
        int node;
        public Pair(int node, double prob) {
            this.node = node;
            this.prob = prob;
        }
    }
    
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        double[] prob = new double[n];
        prob[start] = 1;
        List<Pair>[] graph = new List[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < edges.length; i++) {
            int w = edges[i][0], v = edges[i][1];
            graph[w].add(new Pair(v, succProb[i]));
            graph[v].add(new Pair(w, succProb[i]));
        }
        
        PriorityQueue<Pair> pq = new PriorityQueue<Pair>((a, b) -> Double.compare(b.prob, a.prob));
        pq.offer(new Pair(start, 1));
        while (!pq.isEmpty()) {
            Pair pair = pq.poll();
            int currNode = pair.node;
            double currProb = pair.prob;
            for (Pair nextPair : graph[currNode]) {
                if (prob[nextPair.node] == 0 || currProb * nextPair.prob > prob[nextPair.node]) {
                    prob[nextPair.node] = currProb * nextPair.prob;
                    pq.offer(new Pair(nextPair.node, prob[nextPair.node]));
                }
            }
        }
        
        return prob[end];
    }
}