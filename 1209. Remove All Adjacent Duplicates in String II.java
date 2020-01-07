https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/

解法一：Stack + customized class
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    class Pair {
        int count;
        char ch;
        public Pair(int cnt, char c) {
            count = cnt;
            ch = c;
        }
    }
    public String removeDuplicates(String s, int k) {
        Stack<Pair> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (stack.isEmpty() || c != stack.peek().ch) {
                stack.push(new Pair(1, c));
            } else {
                stack.peek().count++;
                if (stack.peek().count == k) {
                    stack.pop();
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            Pair pair = stack.pop();
            for (int i = 0; i < pair.count; i++) {
                sb.append(pair.ch);
            }
        }
        
        return sb.reverse().toString();
    }
}


解法二：Stack + Two Pointers
时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public String removeDuplicates(String s, int k) {
        char[] arr = s.toCharArray();
        Stack<Integer> countStack = new Stack<>();
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            arr[left++] = arr[right];
            if (left == 1 || arr[left - 1] != arr[left - 2]) {
                countStack.push(1);
            } else {
                int count = countStack.pop() + 1;
                if (count == k) {
                    left -= k;
                } else {
                    countStack.push(count);
                }
            }
        }
        
        return new String(arr, 0, left);
    }
}