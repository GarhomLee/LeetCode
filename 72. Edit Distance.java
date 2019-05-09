https://leetcode.com/problems/edit-distance/

// 总体思路：比较难理解的一道DP题，一般有关两个String的DP问题，要用二维dp数组。
//         dp[i][j]表示从word1[0:i - 1]变为word2[0:j - 1]的最小步数（因为dp数组是1-based），dp[0][j]和dp[i][0]分别表示word1为空和word2为空。
//         根据题目可知，当word1为空时，变为word2所需步数即为word2长度；word2为空时同理。
//         对于0-based字符串，从word1[0:i]变为word2[0:j]，最简单的方法是【只考虑最后一位的变化】。特殊情况，最后一位word1[i]和word2[j]相同，
//         那么最后一位对于字符串变换没有贡献，只需考虑从word1[0:i - 1]变为word2[0:j - 1]即可。
//         一般情况，有三种变化方式：删除，即删除word1[i]，然后从word1[0:i - 1]变为word2[0:j]；插入，即插入word2[j]，在这之前
//         先从word1[0:i]变为word2[0:j - 1]；替换，即把word1[i]变为word2[j]，在这之前先把word1[0:i - 1]变为word2[0:j - 1]，
//         再替换剩下的word1[i]。
//         插入操作对应的是dp[i][j - 1]，删除操作对应的是dp[i - 1][j]，替换操作对应的是dp[i - 1][j - 1]。因为这三个值都已知，所以从三个中
//         选最小值，再+1表示当前的操作，就可以得到dp[i][j]。
//         需要注意：这三个操作【并不只针对word1】，比如插入针对的是word2，而不是word1。




// 解法一：bottom-up DP

class Solution {
    public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();

        int[][] dp = new int[word1.length() + 1][word2.length() + 1];  // dp[i][j] indicates minimum steps to transform the first ith substring (1-based) of word1 to the first jth substring (1-based) of word2
        // dp[0][0] means both word1 and word2 are empty string
        
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;  // word2 is empty string
            for (int j = 1; j <= word2.length(); j++) {
                if (i == 0) {  // word1 is empty string
                    dp[i][j] = j;   
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)){  // word1 and word2 share common last character
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i][j - 1], Math.min(dp[i - 1][j], dp[i - 1][j - 1]));  // choose potential min-step operation and add 1 as current operation
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}

// 解法二：top-down DP

class Solution {
    int[][] dp;
    public int minDistance(String word1, String word2) {
        int length1 = word1.length(), length2 = word2.length();
        dp = new int[length1 + 1][length2 + 1]; 
        for (int row = 0; row <= length1; row++) {
            Arrays.fill(dp[row], -1);
        }
        return getMinDist(word1, word2, length1, length2);
    }
    private int getMinDist(String word1, String word2, int length1, int length2) {
        /* corner cases */
        if (length1 == 0) return length2;
        if (length2 == 0) return length1;
        if (word1.charAt(length1 - 1) == word2.charAt(length2 - 1)) return getMinDist(word1, word2, length1 - 1, length2 - 1);
        /* dp */
        if (dp[length1][length2] != -1) return dp[length1][length2];
        dp[length1][length2] = 1 + Math.min(Math.min(getMinDist(word1, word2, length1 - 1, length2), getMinDist(word1, word2, length1, length2 - 1)), getMinDist(word1, word2, length1 - 1, length2 - 1));
        return dp[length1][length2];
    }
}