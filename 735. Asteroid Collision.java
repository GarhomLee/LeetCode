https://leetcode.com/problems/asteroid-collision/

思路：Stack

时间复杂度：O(n)
空间复杂度：O(n)
犯错点：1.循环条件错误：
        2.判断条件错误：

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        for (int size : asteroids) {
            if (size < 0 && !stack.isEmpty() && stack.peek() >= 0) {
                //while (!stack.isEmpty() 0 && -size > stack.peek())  // {Mistake 1}
                while (!stack.isEmpty() && stack.peek() >= 0 && -size > stack.peek()) {  // {Correction 1}
                    // current asteroid going left and the top asteroid of the stack going right, 
                    // which explodes because it is smaller
                    stack.pop();
                }
                
                //if (stack.isEmpty())  // {Mistake 2}
                if (stack.isEmpty() || stack.peek() < 0) {  // {Correction 2}
                    // no asteroid going left, or the top asteroid of the stack also going left
                    stack.push(size);
                } else if (stack.peek() == -size) {
                    // same size, explode
                    stack.pop();
                }
            } else {
                stack.add(size);
            }
        }
        
        int[] res = new int[stack.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = stack.pop();
        }
        
        return res;
    }
}