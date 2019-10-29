https://leetcode.com/problems/baseball-game/

// 思路：Stack
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int calPoints(String[] ops) {
        Stack<Integer> stack = new Stack<>();
        for (String s : ops) {
            if (s.equals("C")) {
                // remove the last points
                if (stack.isEmpty()) continue;  // skip when there is nothing to remove
                stack.pop();
            } else if (s.equals("D")) {
                // double the last points
                int top = stack.peek();
                stack.push(top * 2);
            } else if (s.equals("+")) {
                // add up the last two points
                int top = stack.pop();
                int sum = stack.peek() + top;
                stack.push(top);
                stack.push(sum);
            } else {
                // get new points
                int curr = Integer.parseInt(s);
                stack.push(curr);
            }
        }
        
        int res = 0;
        while (!stack.isEmpty()) {
            // add all valid points into res
            res += stack.pop();
        }
        
        return res;
    }
}