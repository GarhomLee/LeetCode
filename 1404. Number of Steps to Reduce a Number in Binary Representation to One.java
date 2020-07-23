https://leetcode.com/problems/number-of-steps-to-reduce-a-number-in-binary-representation-to-one/

idea: Simulation
time complexity: O(n)
space complexity: O(1)

class Solution {
    public int numSteps(String s) {
        int ret = 0, n = s.length(), carry = 0;
        for (int i = n - 1; i >= 1; i--) {
            ret++;
            
            if (s.charAt(i) - '0' + carry == 1) {
                ret++;
                carry = 1;
            }
        }
        
        return ret + carry;
    }
}