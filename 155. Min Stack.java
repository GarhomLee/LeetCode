https://leetcode.com/problems/min-stack/

// 思路：push():每次push进两个元素：第一个是x，第二个是包含x在内的所有Stack元素的最小值。
//      pop():每次pop都要移除两个元素
//      top():先将顶部元素pop出来，然后peek接下来的顶部元素
//      getMin():直接peek顶部元素

class MinStack {
    Stack<Integer> stack;
    
    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack();
    }
    
    public void push(int x) {
        int min = stack.isEmpty() ? Integer.MAX_VALUE : stack.peek();
        min = Math.min(min, x);
        stack.push(x);
        stack.push(min);
    }
    
    public void pop() {
        stack.pop();
        stack.pop();
    }
    
    public int top() {
        int min = stack.pop();
        int top = stack.peek();
        stack.push(min);
        return top;
    }
    
    public int getMin() {
        return stack.peek();
    }
}

// 另一种写法：只在min变换时多做一次push或pop操作