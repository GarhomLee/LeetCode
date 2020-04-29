https://leetcode.com/problems/validate-stack-sequences/

idea: Stack
time complexity: O(n)
space complexity: O(1)

class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        // mimic the process of pushing and popping
        int top = -1, pushIdx = 0, popIdx = 0;
        while (pushIdx < pushed.length && popIdx < popped.length) {
            if (top == -1 || pushed[top] != popped[popIdx]) {
                pushed[++top] = pushed[pushIdx++];
            } else {
                top--;
                popIdx++;
            }
        }
        
        // pushing is done, then finish the popping
        while (top >= 0 && popIdx < popped.length && pushed[top] == popped[popIdx]) {
            top--;
            popIdx++;
        }
        
        // check if the stack is empty and all elements in popped array have popped
        return top == -1 && popIdx == popped.length;
    }
}