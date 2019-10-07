https://leetcode.com/problems/count-vowels-permutation/

// 解法一：Recursion with Memoization 
//         递归函数定义：long dfs(char c, int n, Long[][] dp)，表示以字符c为起始字符的n个字符能组成的符合题意
//             的permutation个数。
//         终止条件：n==1，只能组成1个permutation，返回1
//         递归过程：根据当前字符c，选取下一个符合题意的字符，搜索其结果，将每个结果相加，存进dp数组中。
// 时间复杂度：O(26*n)=O(n)
// 空间复杂度：O(26*n)=O(n)
// 犯错点：1.数据溢出：需要用long类型储存每一步结果，且每一步都需要mod。

class Solution {
    final int MOD = 1_000_000_007;
    char[] arr = new char[]{'a', 'e', 'i', 'o', 'u'};
    
    public int countVowelPermutation(int n) {
        Long[][] dp = new Long[26][n + 1];
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += dfs(arr[i], n, dp) % MOD;
            count = count % MOD;
        }
        
        return (int) count;
    }
    
    private long dfs(char c, int n, Long[][] dp) {
        if (n == 1) {
            return 1;
        }
        if (dp[c - 'a'][n] != null) {
            return dp[c - 'a'][n];
        }
        
        long res = 0;
        switch (c) {
            case 'a': 
                res = dfs('e', n - 1, dp) % MOD;
                break;
            case 'e':
                res = (dfs('a', n - 1, dp) % MOD + dfs('i', n - 1, dp) % MOD) % MOD;
                break;
            case 'i':
                for (char next: arr) {
                    if (next == 'i') continue;
                    res += dfs(next, n - 1, dp) % MOD;
                    res = res % MOD;
                }
                break;
            case 'o':
                res = (dfs('i', n - 1, dp) % MOD + dfs('u', n - 1, dp) % MOD) % MOD;
                break;
            case 'u':
                res = dfs('a', n - 1, dp) % MOD;
                break;
        }
        
        dp[c - 'a'][n] = res % MOD;
        return dp[c - 'a'][n];
    }
}


解法二：Bottom-up DP，代码优化，见discussion。 