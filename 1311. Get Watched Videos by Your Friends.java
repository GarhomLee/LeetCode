https://leetcode.com/problems/get-watched-videos-by-your-friends/

思路：Graph + BFS + HashMap + PriorityQueue (Sort)

时间复杂度：O(V + E + n log n), n=num of videos
空间复杂度：O(V + n), n=num of videos

class Solution {
    class Node {
        String video;
        int freq;
        public Node(String v, int f) {
            video = v;
            freq = f;
        }
    }
    
    public List<String> watchedVideosByFriends(List<List<String>> watchedVideos, int[][] friends, int id, int k) {
        int len = friends.length;
        List<String> res = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[len];
        queue.offer(id);
        visited[id] = true;
        int level = 0;
        while (level < k && !queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                for (int next : friends[curr]) {
                    if (!visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
            }
            level++;
        }
        
        Map<String, Integer> map = new HashMap<>();
        while (!queue.isEmpty()) {
            int curr = queue.poll();
            for (String s : watchedVideos.get(curr)) {
                map.put(s, map.getOrDefault(s, 0) + 1);
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.freq == b.freq ? a.video.compareTo(b.video) : a.freq - b.freq);
        for (String s : map.keySet()) {
            pq.offer(new Node(s, map.get(s)));
        }
        
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            res.add(node.video);
        }
        
        return res;
    }
}