https://leetcode.com/problems/implement-stack-using-queues/

// 解法一：Two Queues
//      类似232. Implement Queue using Stacks，永远维护tempQueue为空，作为临时Stack，而stackQueue进行pop()和peek()。
//      每加入新元素，将stackQueue全部转移到tempQueue，将新元素放进stackQueue头部，再将tempQueue放回stackQueue，所以
//      stackQueue头部还是最新加入的元素。
// 时间复杂度：Push - O(n) per operation, Pop - O(1) per operation.
// 空间复杂度：O(n)

class MyStack {
    Deque<Integer> stackQueue, tempQueue;
    
    /** Initialize your data structure here. */
    public MyStack() {
        stackQueue = new ArrayDeque();
        tempQueue = new ArrayDeque();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        /* 写法一 */
        while (!stackQueue.isEmpty()) {
            tempQueue.offer(stackQueue.poll());
        }
        stackQueue.offer(x);
        while (!tempQueue.isEmpty()) {
            stackQueue.offer(tempQueue.poll());
        }

        /* 写法二：将新加入元素直接push进tempQueue，然后将stackQueue元素全部转移进tempQueue，最后交换stackQueue和tempQueue */
        /*tempQueue.offer(x);
        while (!stackQueue.isEmpty()) {
            tempQueue.offer(stackQueue.poll());
        }
        Deque<Integer> q = tempQueue;
        tempQueue = stackQueue;
        stackQueue = q;*/
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return stackQueue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return stackQueue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return stackQueue.isEmpty();
    }
}

// 解法二：One Queue
//         加入新元素前，记录当前Queue的size。将新元素加入尾部后，在头部poll出之前记录的size个元素，依次加入尾部。
//         这样就保证了头部还是最新加入的元素。
// 时间复杂度：Push - O(n) per operation, Pop - O(1) per operation.
// 空间复杂度：O(n)

class MyStack {
    Queue<Integer> queue;

    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        int size = queue.size();
        queue.add(x);
        for (int i = 0; i < size; i++) {
            queue.add(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}