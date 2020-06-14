https://leetcode.com/problems/online-stock-span/

idea: Monotonic Queue, implemented by Stack
time complexity: 
    next(): amortized O(1)
space complexity: O(n)

class StockSpanner {
    Stack<int[]> stack;   // use a stack to implement strictly decreasingly monotonic queue
    int day;
    
    public StockSpanner() {
        stack = new Stack<>();
        day = 0;
    }
    
    public int next(int price) {
        int[] pair = new int[]{price, day};
        while (!stack.isEmpty() && stack.peek()[0] <= price) {
            stack.pop();
        }
        
        int res = stack.isEmpty() ? day + 1: day - stack.peek()[1];
        day++;
        stack.push(pair);
        return res;
    }
}

/**
 * Your StockSpanner object will be instantiated and called as such:
 * StockSpanner obj = new StockSpanner();
 * int param_1 = obj.next(price);
 */