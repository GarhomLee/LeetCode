https://leetcode.com/problems/check-if-two-expression-trees-are-equivalent/

idea: DFS
    -Count the occurrence of each char given the operand is only '+'.
time complexity: O(n)
space complexity: O(26 + h)

class Solution {
    private void dfs(Node node, int[] count) {
        if (node == null) return;
        
        if (node.val != '+') {
            count[node.val - 'a']++;
        }
        dfs(node.left, count);
        dfs(node.right, count);
    }
    
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] count1 = new int[26], count2 = new int[26];
        dfs(root1, count1);
        dfs(root2, count2);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}


follow-up: '-' is also supported

class Solution {
    private void dfs(Node node, int[] count, int sign) {
        if (node == null) return;
        
        if (node.val == '+') {
            dfs(node.left, count, sign);
            dfs(node.right, count, sign);
        } else if (node.val == '-') {
            dfs(node.left, count, sign);
            dfs(node.right, count, -sign);  // only change the sign of the right children
        } else {
            count[node.val - 'a'] += sign;
        }
    }
    
    public boolean checkEquivalence(Node root1, Node root2) {
        int[] count1 = new int[26], count2 = new int[26];
        dfs(root1, count1, 1);
        dfs(root2, count2, 1);
        for (int i = 0; i < 26; i++) {
            if (count1[i] != count2[i]) {
                return false;
            }
        }
        return true;
    }
}