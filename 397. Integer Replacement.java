https://leetcode.com/problems/integer-replacement/

// 解法一：用recursion。注意【将参数设为long】避免越界。
// 注意：不需要用DP。理由：
//      1）没有合适的重复子问题结构，不符合DP条件。如果n为偶数，那么结果是确定的，不和任何其他n有关。
//      2）如果用dp，n很大时O(n)空间会导致Memory Limit Exceeded。

class Solution {
    public int integerReplacement(int n) {
        return replace((long) n);
    }
    private int replace(long n) {
        int ans = 0;
        if (n <= 1) return 0;
        if (n % 2 == 0) ans = 1 + replace(n / 2);
        else ans = 1 + Math.min(replace(n + 1), replace(n - 1));
        return ans;
    }
}


// 解法二：Bit Manipulation
//        如果n为偶数（(n & 1) == 0），直接更新n为n / 2（或用n >>> 1）。
//        如果n为奇数，要判断是+1还是-1，利用n / 2的奇偶性。因为下一个loop里n必为偶数，所以在+1和-1里选步数较少的一个。
//        可以发现，(n-1)/2和n/2是一致的，所以利用n/2来判断n要不要-1
//        1）如果n / 2为奇数，那么 -1 和 /2 两步以后也就是这个奇数，步数会更多，所以不选-1，而选+1。选了+1，那么 +1 和 /2 两步以后
//           是偶数，又可以直接 /2 ，所以步数更少。
//        2）如果n / 2为偶数，那么 -1 和 /2 两步以后也就是这个偶数，步数会更少，所以选择-1。
//        3）如果n==3，那么直接选择-1，因为3->2->1比3->4->2->1步数少
// 犯错点：1.&操作要用括号括起来
//        2.右移操作要用unsigned的>>>

class Solution {
    public int integerReplacement(int n) {
        int count = 0;
        while (n != 1) {
            count++;
            
            //if (n & 1 == 0)  // {Mistake 1: & has to be in parenthesis}
            if ((n & 1) == 0) {  // n is even number
                //n = n >> 1;  // {Mistake 2: test case Integer.MAX_VALUE}
                n = n >>> 1;  //  {Correction 2: must use unsigned right shift}
            } else if (n == 3 || ((n >>> 1) & 1) == 0) {  // n/2 is even number
                n--;
            } else {
                n++;
            }
        }
        return count;
    }
}