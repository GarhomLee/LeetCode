https://leetcode.com/problems/prime-arrangements/

思路：Math

class Solution {
    public int numPrimeArrangements(int n) {
        int m = countPrimes(n+1), M = 1000000007;
        long count = 1;
        for (int i = m; i > 0; i--) {
            count = (count*i)%M;
        }
        for (int i = n-m; i > 0; i--) {
            count = (count*i)%M;
        }
        return (int)count;
    }
    
    private int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }
        
        for (int i = 2; i*i < n; i++) {
            if (!isPrime[i]) {
                continue;
            }
            for (int j = i*i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        return count;
    }
}