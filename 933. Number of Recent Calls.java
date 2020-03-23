https://leetcode.com/problems/number-of-recent-calls/

solution: Queue
    Simply compare the time of the head in Queue with the query time t, remove those
    greater than 3000ms window size.
time complexity: O(Q), where Q is the number of queries made
space complexity: O(n), n=max number of pings

class RecentCounter {
    Queue<Integer> queue;
    
    public RecentCounter() {
        queue = new LinkedList<>();
    }
    
    public int ping(int t) {
        queue.offer(t);
        while (!queue.isEmpty() && t - queue.peek() > 3000) {
            queue.poll();
        }
        
        return queue.size();
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */