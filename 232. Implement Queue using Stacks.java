https://leetcode.com/submissions/detail/216025841/

// 写法一：永远维护stack2为空，作为临时Stack，而stack1进行pop()和peek()。
//     每加入新元素，将stack1全部转移到stack2，将新元素放进stack1底部，再将stack2放回stack1，所以stack1顶部还是最早加入的元素。
// 时间复杂度：Push - O(n) per operation, Pop - O(1) per operation.
// 空间复杂度：O(n)

class MyQueue {
    private Stack<Integer> stack1, stack2;
    /** Initialize your data structure here. */
    public MyQueue() {
        stack1 = new Stack<Integer>();
        stack2 = new Stack<Integer>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        while (!stack1.isEmpty()) stack2.push(stack1.pop());
        stack1.push(x);
        while (!stack2.isEmpty()) stack1.push(stack2.pop());
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        return stack1.pop();
    }
    
    /** Get the front element. */
    public int peek() {
        return stack1.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack1.isEmpty();
    }
}


// 写法二：一个inStack，一个outStack，当调用pop()和peek()时，只有当outStack为空时，才将inStack转移到outStack。
// 时间复杂度：Push - O(1) per operation, Pop - Amortized O(1) per operation.
// 空间复杂度：O(n)

class MyQueue {
    Stack<Integer> inStack, outStack;
    
    /** Initialize your data structure here. */
    public MyQueue() {
        inStack = new Stack();
        outStack = new Stack();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) transfer();
        return outStack.pop(); 
    }
    
    /** Get the front element. */
    public int peek() {
        if (outStack.isEmpty()) transfer();
        return outStack.peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
    
    private void transfer() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}