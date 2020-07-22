https://leetcode.com/problems/queries-on-a-permutation-with-key/

idea: Brute force
time complexity: O(n*m)
space complexity: O(m)

class Solution {
    public int[] processQueries(int[] queries, int m) {
        int n = queries.length;
        int[] res = new int[n];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            list.add(i + 1);
        }
        
        for (int i = 0; i < n; i++) {
            int index = list.indexOf(queries[i]);
            int num = list.get(index);
            list.remove(index);
            list.add(0, num);
            res[i] = index;
        }
        
        return res;
    }
}