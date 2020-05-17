https://leetcode.com/problems/simplified-fractions/

idea: Math (GCD template)
time complexity: O(n^2 * log n)
space complexity: O(n^2) (or O(log n) or GCD recursion)

class Solution {
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        
        return gcd(b, a % b);
    }
    
    public List<String> simplifiedFractions(int n) {
        List<String> res = new ArrayList<>();
        
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                if (gcd(i, j) == 1) {
                    res.add(j + "/" + i);
                }
            }
            
        }
        
        
        return res;
    }
}