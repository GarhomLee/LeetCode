https://leetcode.com/problems/rank-transform-of-an-array/

思路：HashMap + sort
时间复杂度：O(n log n)
空间复杂度：O(n)

class Solution {
    public int[] arrayRankTransform(int[] arr) {
        int len = arr.length;
        int[] res = new int[len], copy = new int[len];
        System.arraycopy(arr, 0, copy, 0, len);
        Arrays.sort(copy);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int num : copy) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        
        for (int i = 0; i < len; i++) {
            res[i] = map.get(arr[i]);
        }
        return res;
    }
}