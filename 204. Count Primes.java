https://leetcode.com/problems/count-primes/

// 要利用一些数学性质。
// 1）维护一个size为n的boolean数组isPrime（因为题目为小于n的所prime number个数），初始化为true。
// 2）维护count表示prime number个数
// 3）外层循环：从2开始，查找是否被标记为素数。如果为true，count++，然后进入内层循环
// 4）内层循环：从2开始，和外层循环找到的数的乘积必然不是素数，所以将这些数全部标记为false。由于从2一直标记到n - 1，所以
//     可以保证在外层循环中找到的素数一定为真素数

class Solution {
    public int countPrimes(int n) {
        if (n < 2) return 0;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i] == true) {
                count++;
                for (int j = 2; i * j < n; j++) {
                    isPrime[i * j] = false;
                }
            }
        }
        return count;
    }
}