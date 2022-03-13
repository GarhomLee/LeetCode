https://leetcode.com/problems/design-bounded-blocking-queue/

idea1: Use synchronized + wait()/notify(), which is utilizing the monitor

class BoundedBlockingQueue {
    Queue<Integer> queue;
    int cap;
    
    public BoundedBlockingQueue(int capacity) {
        queue = new LinkedList<>();
        cap = capacity;
    }
    
    public void enqueue(int element) throws InterruptedException {
        synchronized(this) {
            while (queue.size() == cap) wait();
            
            queue.offer(element);
            notify();
        }
    }
    
    public int dequeue() throws InterruptedException {
        synchronized(this) {
            while (queue.isEmpty()) wait();
            
            int ret = queue.poll();
            notify();
            return ret;
        }
    }
    
    public int size() {
        return queue.size();
    }
}


idea2: Semaphore