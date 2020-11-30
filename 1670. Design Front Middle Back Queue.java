https://leetcode.com/problems/design-front-middle-back-queue/

idea: Double-ended queue (Deque)
    -Maintain the invariant that leftDeque size is either the same or at most 1 smaller
     than rightDeque size.
time complexity: O(1)
space complexity: O(n)

class FrontMiddleBackQueue {
    Deque<Integer> leftDeque;
    Deque<Integer> rightDeque;
    
    public FrontMiddleBackQueue() {
        leftDeque = new LinkedList<>();
        rightDeque = new LinkedList<>();
    }
    
    public void pushFront(int val) {
        leftDeque.addFirst(val);
        if (leftDeque.size() > rightDeque.size()) {
            rightDeque.addFirst(leftDeque.removeLast());
        }
    }
    
    public void pushMiddle(int val) {
        leftDeque.addLast(val);
        if (leftDeque.size() > rightDeque.size()) {
            rightDeque.addFirst(leftDeque.removeLast());
        }
    }
    
    public void pushBack(int val) {
        if (leftDeque.size() < rightDeque.size()) {
           leftDeque.addLast(rightDeque.removeFirst());
        }
        rightDeque.addLast(val);
    }
    
    public int popFront() {
        if (leftDeque.size() < rightDeque.size()) {
           leftDeque.addLast(rightDeque.removeFirst());
        }
        return leftDeque.isEmpty() ? -1 : leftDeque.removeFirst();
    }
    
    public int popMiddle() {
        if (leftDeque.size() < rightDeque.size()) {
           return rightDeque.removeFirst();
        }
        return leftDeque.isEmpty() ? -1 : leftDeque.removeLast();
    }
    
    public int popBack() {
        int ret = rightDeque.isEmpty() ? -1 : rightDeque.removeLast();
        if (leftDeque.size() > rightDeque.size()) {
            rightDeque.addFirst(leftDeque.removeLast());
        }
        return ret;
    }
}

/**
 * Your FrontMiddleBackQueue object will be instantiated and called as such:
 * FrontMiddleBackQueue obj = new FrontMiddleBackQueue();
 * obj.pushFront(val);
 * obj.pushMiddle(val);
 * obj.pushBack(val);
 * int param_4 = obj.popFront();
 * int param_5 = obj.popMiddle();
 * int param_6 = obj.popBack();
 */