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

class BoundedBlockingQueue {
    Queue<Integer> queue;
    Semaphore prodSem, consSem;
    
    public BoundedBlockingQueue(int capacity) {
        queue = new LinkedList<>();
        prodSem = new Semaphore(capacity);
        consSem = new Semaphore(0);
    }
    
    public void enqueue(int element) throws InterruptedException {
        prodSem.acquire();
        queue.offer(element);
        consSem.release();
    }
    
    public int dequeue() throws InterruptedException {        
        consSem.acquire();
        int ret = queue.poll();
        prodSem.release();
        
        return ret;
    }
    
    public int size() {
        return queue.size();
    }
}