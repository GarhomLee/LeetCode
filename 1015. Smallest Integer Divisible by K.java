https://leetcode.com/problems/smallest-integer-divisible-by-k/

idea: Math, pigeon holes problem. Referring to: https://leetcode.com/problems/smallest-integer-divisible-by-k/discuss/260852/JavaC%2B%2BPython-O(1)-Space-with-Proves-of-Pigeon-Holes
time complexity: O(K)
space complexity: O(1)

class Solution {
    public int smallestRepunitDivByK(int k) {
        
        if (k == 2 || k == 5) {
            return -1;
        }
        
        int num = 0;
        for (int len = 1; len <= k; len++) {
            num = (num * 10 + 1) % k;
            if (num == 0) {
                return len;
            }
        }
        
        return -1;
    }
}