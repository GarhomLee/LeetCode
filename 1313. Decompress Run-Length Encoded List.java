https://leetcode.com/problems/decompress-run-length-encoded-list/

思路：按题意直接求
时间复杂度：O(sum(count))
空间复杂度：O(sum(count))

class Solution {
    public int[] decompressRLElist(int[] nums) {
        int len = nums.length;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; 2 * i + 1< len; i++) {
            int count = nums[2 * i], value = nums[2 * i + 1];
            for (int j = 0; j < count; j++) {
                list.add(value);
            }
        }
        
        int[] res = new int[list.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i);
        }
        return res;
    }
}