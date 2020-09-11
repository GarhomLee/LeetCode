https://leetcode.com/problems/design-a-stack-with-increment-operation/

idea: Stack + Info Cache.
    -Use an extra array to cache the point below where all elements get increased. 
     The increment gets effect only during pop operation.

time complexity: O(1) for each operation
space complexity: O(n) in total

class CustomStack {
    int[] stack;
    int[] incr;
    int n;  // max capacity
    int size;   // current stack size
        
    public CustomStack(int maxSize) {
        n = maxSize;
        size = 0;
        stack = new int[n];
        incr = new int[n];
    }
    
    public void push(int x) {
        if (size == n) return;
        stack[size++] = x;
    }
    
    public int pop() {
        if (size == 0) {
            return -1;
        }
        if (size > 1) {
            incr[size - 2] += incr[size - 1];
        }
        int res = stack[size - 1] + incr[size - 1];
        incr[size - 1] = 0;
        size--;
        return res;
    }
    
    public void increment(int k, int val) {
        int incrLen = Math.min(k, size);
        if (incrLen == 0) return;
        incr[incrLen - 1] += val;
    }
}