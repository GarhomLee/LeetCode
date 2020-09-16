https://leetcode.com/problems/replace-all-s-to-avoid-consecutive-repeating-characters/

idea: Greedy?

class Solution {
    public String modifyString(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();

        // '?' at position 0
        if (arr[0] == '?') {
            if (n == 1) {
                arr[0] = 'a';
            } else {
                arr[0] = arr[1] == 'a' ? 'b' :'a';
            }
        }
        
        for (int i = 1; i < n; i++) {
            if (arr[i] == '?') {
                // determined by left and right char, it can only have 3 choices: 'a', 'b', or 'c'
                char left = arr[i - 1];
                char right = i == n - 1 ? '?' : arr[i + 1];
                boolean usedA = (left == 'a' || right == 'a');
                boolean usedB = (left == 'b' || right == 'b');
                
                if (usedA && usedB) {
                    arr[i] = 'c';
                } else if (usedA) {
                    arr[i] = 'b';
                } else {
                    arr[i] = 'a';
                }
            }
        }
        
        return new String(arr);
    }
}