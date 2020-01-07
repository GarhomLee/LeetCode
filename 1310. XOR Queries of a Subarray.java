https://leetcode.com/problems/xor-queries-of-a-subarray/

思路：Bit Manipulation，类似prefix sum
        由于数字num ^ num结果为0，因此[i:j]的结果=[0:j]的结果^[0:i]的结果。
时间复杂度：O(n+m), n=arr.length, m=queries.length
空间复杂度：O(n+m), n=arr.length, m=queries.length

class Solution {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] res = new int[queries.length];
        int[] xor = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            xor[i] = xor[i - 1] ^ arr[i - 1];
        }
        for (int i = 0; i < queries.length; i++) {
            int left = queries[i][0], right = queries[i][1] + 1;
            res[i] = xor[left] ^ xor[right];
        }
        
        return res;
    }
}