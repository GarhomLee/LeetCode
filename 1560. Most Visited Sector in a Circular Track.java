https://leetcode.com/problems/most-visited-sector-in-a-circular-track/

idea: https://leetcode.com/problems/most-visited-sector-in-a-circular-track/discuss/806814/JavaC%2B%2BPython-From-Start-to-End
time complexity: O(n)
space complexity: O(1)

class Solution {
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int start = rounds[0], end = rounds[rounds.length - 1];
        if (start <= end) {
            for (int i = start; i <= end; i++) {
                res.add(i);
            }
        } else {
            for (int i = 1; i <= end; i++) {
                res.add(i);
            }
            for (int i = start; i <= n; i++) {
                res.add(i);
            }
        }
        return res;
    }
}