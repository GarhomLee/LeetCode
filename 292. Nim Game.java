https://leetcode.com/problems/nim-game/

// Brute Force：DP，如果拿走1个、2个或3个石头能导致输掉的，那么当前石头的数量我能赢，因为只要拿掉相应的石头数量对方就输。
// 出现的问题：Memory limit exceed

class Solution {
    public boolean canWinNim(int n) {
        if (n < 4) return true;
        boolean[] dp = new boolean[n + 1];
        dp[1] = true;
        dp[2] = true;
        dp[3] = true;
        for (int i = 4; i <= n; i++) {
            dp[i] = !dp[i - 1] || !dp[i - 2] || !dp[i - 3];
        }
        return dp[n];
    }
}


// 优化思路：如果起手的石头数量是4的倍数，那么一定输，否则就能赢。

class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}