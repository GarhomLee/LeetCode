https://leetcode.com/problems/minimum-absolute-difference/

// 思路：Sort
// 时间复杂度：O(n log n)
// 空间复杂度：O(n)

class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        Arrays.sort(arr);
        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i + 1 < arr.length; i++) {
            minDiff = Math.min(minDiff, arr[i + 1] - arr[i]);
        }
        
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i + 1 < arr.length; i++) {
            if (arr[i + 1] - arr[i] == minDiff) {
                res.add(Arrays.asList(arr[i], arr[i + 1]));
            }
        }
        
        return res;
    }
}