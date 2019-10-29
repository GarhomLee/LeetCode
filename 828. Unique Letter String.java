https://leetcode.com/problems/unique-letter-string/

思路：Two Pointer，记录每个char最近出现的两个位置。参考：https://leetcode.com/problems/unique-letter-string/discuss/128952/One-pass-O(N)-Straight-Forward

时间复杂度：O(N)
空间复杂度：O(N)

class Solution {
    final int MOD = 1_000_000_007;
    
    public int uniqueLetterString(String S) {
        int len = S.length();
        int[][] index = new int[26][2];  // record the mostly recent 2 occurrence
                                         // of chars
        /* initialize index array */
        for (int i = 0; i < 26; i++) {
            Arrays.fill(index[i], -1);
        }
        
        long count = 0;
        for (int i = 0; i < len; i++) {
            char c = S.charAt(i);
            if (index[c - 'A'][1] == -1) {
                index[c - 'A'][1] = i;
            } else {
                int left = index[c - 'A'][1] - index[c - 'A'][0];
                int right = i - index[c - 'A'][1];
                count += (left * right) % MOD;
                count %= MOD;
                index[c - 'A'][0] = index[c - 'A'][1];  // update
                index[c - 'A'][1] = i;
            }
        }
        
        /* count when virtual right border is at the end */
        for (int i = 0; i < 26; i++) {
            if (index[i][1] == -1) continue;
            
            int left = index[i][1] - index[i][0];
            int right = len - index[i][1];
            count += (left * right) % MOD;
            count %= MOD;
        }
        
        return (int) count;
    }
}