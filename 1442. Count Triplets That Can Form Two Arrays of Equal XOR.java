https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/

idea: Bit Manipulation. Referring to: solution 3 in https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/discuss/623747/JavaC%2B%2BPython-One-Pass-O(N4)-to-O(N)
    The trick is that, give subarray a=arr[i]^...arr[j-1], b=arr[j]^...arr[k],
    if a == b, then a^b == 0, and this is true for any index i < j <= k, therefore
    the count is k - i.
time complexity: O(n^2)
space complexity: O(n) -> can be optimized, see https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/discuss/623754/Java-Use-the-Trick-of-XOR-operation!!!

class Solution {
    public int countTriplets(int[] arr) {
        int n = arr.length;
        
        int[] xor = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            xor[i] = xor[i - 1] ^ arr[i - 1];
        }
        
        int count = 0;
        for (int right = 2; right <= n; right++) {
            for (int left = 0; left + 2 <= right; left++) {
                if (xor[left] == xor[right]) {
                    count += right - left - 1;
                }
            }
        }
        
        return count;
    }
}