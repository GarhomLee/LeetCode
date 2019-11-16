https://leetcode.com/problems/campus-bikes/

解法一 (Intuition)：minHeap

时间复杂度：O(m * n * log(m * n))
空间复杂度：O(m * n)

class Solution {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[] res = new int[n];
        Set<Integer> seenWorkers = new HashSet<>();
        Set<Integer> seenBikes = new HashSet<>();
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                if (n1.dist != n2.dist) {
                    return n1.dist - n2.dist;
                } else if (n1.wid != n2.wid) {
                    return n1.wid - n2.wid;
                } else {
                    return n1.bid - n2.bid;
                }
            }
        });
        for (int wid = 0; wid < n; wid++) {
            for (int bid = 0; bid < m; bid++) {
                int dist = getDist(workers[wid], bikes[bid]);
                pq.offer(new Node(dist, bid, wid));
            }
        }
        
        int count = 0;
        while (count < n) {
            Node node = pq.poll();
            // System.out.println("dist="+node.dist+", wid="+node.wid+", bid="+node.bid);
            if (seenWorkers.contains(node.wid) || seenBikes.contains(node.bid)) continue;
            
            res[node.wid] = node.bid;
            seenWorkers.add(node.wid);
            seenBikes.add(node.bid);
            count++;
        }
    
        return res;
    }
    
    private int getDist(int[] arr1, int[] arr2) {
        return Math.abs(arr1[0] - arr2[0]) + Math.abs(arr1[1] - arr2[1]);
    }
    
    class Node {
        int dist, bid, wid;
        
        public Node(int d, int b, int w) {
            dist = d;
            bid = b;
            wid = w;
        }
    }
}


解法二（优化）：Bucket Sort

时间复杂度：O(m * n)
空间复杂度：O(m * n)

class Solution {
    private int getDist(int[] arr1, int[] arr2) {
        return Math.abs(arr1[0] - arr2[0]) + Math.abs(arr1[1] - arr2[1]);
    }
    
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length, m = bikes.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);  // initialized res array to avoid using another boolean array
        List<int[]>[] buckets = new List[2000];  // max distance is less than 1999
        
        // assign the highest priority to the worker with the smaller index  
        for (int wid = 0; wid < n; wid++) {
            // assign the second highest priority to the bike with the smaller index
            for (int bid = 0; bid < m; bid++) {
                int dist = getDist(workers[wid], bikes[bid]);
                if (buckets[dist] == null) {
                    buckets[dist] = new ArrayList<int[]>();
                }
                buckets[dist].add(new int[]{wid, bid});
            }
        }
        
        boolean[] seenBikes = new boolean[m];
        for (int dist = 0; dist < buckets.length; dist++) {
            if (buckets[dist] == null) continue;  // skip the distance which does not exist
            
            for (int i = 0; i < buckets[dist].size(); i++) {
                int[] pair = buckets[dist].get(i);
                int wid = pair[0], bid = pair[1];
                
                if (seenBikes[bid] || res[wid] != -1) continue;  // skip the worker who has already got the bike, as well as the used bike
                
                res[wid] = bid;
                seenBikes[bid] = true;
            }
        }
        
        return res;
    }
    
    
}