https://leetcode.com/problems/lexicographically-smallest-string-after-applying-operations/

idea: DFS
    -Separate rotation and increment.
time complexity: O(n*10)
space complexity: O(n^2), for creating new String

class Solution {
    private void rotate(String curr, int incr, int rotateLen, TreeSet<String> seen) {
        int n = curr.length();
        while (!seen.contains(curr)) {
            seen.add(curr);
            increment(curr, incr, rotateLen, seen);
            curr = curr.substring(n - rotateLen) + curr.substring(0, n - rotateLen);
        }
    }
    
    private void increment(String curr, int incr, int rotateLen, TreeSet<String> seen) {
        StringBuilder sb = new StringBuilder(curr);
        int n = curr.length();
        for (int i = 0; i < 10; i++) {
            for (int j = 1; j < n; j += 2) {
                int num = sb.charAt(j) - '0';
                num = (num + incr) % 10;
                sb.setCharAt(j, (char) ('0' + num));
            }
            rotate(sb.toString(), incr, rotateLen, seen);
        }
    }
    
    public String findLexSmallestString(String s, int a, int b) {
        TreeSet<String> seen = new TreeSet<>();
        rotate(s, a, b, seen);
        
        return seen.first();
    }
}