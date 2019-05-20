https://leetcode.com/problems/russian-doll-envelopes/

// 总体思路：根据题意，当将envelopes按width排序后，就转化为了300. Longest Increasing Subsequence问题，而且条件更严格，因为需要width和height都比前一个元素大。

解法一：DP，维护一维数组dp，dp[i]表示包含envelopes在内的increasing subsequence长度
时间复杂度：O(n^2), n = envelopes.length
空间复杂度：O(n), n = envelopes.length
犯错点：1.当generic type为int[]时Comparator的写法

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        
        /* sort the envelopes by the width */
        Arrays.sort(envelopes, new Comparator<int[]>() {  // {Mistake 1: generic type should be specific for int array} {Correction 1}
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[0] - e2[0];
            }
        });
        
        /* transform to the longest increasing subsequence question */
        int[] dp = new int[envelopes.length];  // dp[i] indicates the length of increasing subsequence including envelopes[i]
        int maxLen = 0;
        for (int i = 0; i < envelopes.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1] && envelopes[i][0] > envelopes[j][0]) {  // more strigent condition for updating dp[i]
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}

// 解法二：耐心排序。类似300. Longest Increasing Subsequence的耐心排序辅助数组，维护二维tails数组，对于tails[i][j]，i表示长度为i + 1的increasing subsequence，
//         tails[i][j]表示对于所有长度为i + 1的increasing subsequence中【末尾元素的height最小的envelope】。
//         遍历所有envelopes，对于当前envelopes[i]，如果height比tails[maxLen - 1]的height还大，那么把它加到tails的末尾，同时更新maxLen；如果height比tails[0]的height还小，
//         那么更新tails[0]；对于其他情况，需要用Binary Search找到比当前envelope的height大的最小元素tails[index]，并更新之，使得tails依然满足上述性质。
//         由于题目更严格的条件限制，需要width和height都比前一个元素大，所以在对envelopes进行预先排序时，要按照先对width从小到大排列，再对【width相同的情况从大到小排列】，
//         这样当更新tails[maxLen]时envelopes[i]的width一定是最大的。而对于height处于中间值的情况，即使envelopes[i]的width比tails数组最后一个元素的width大，而height却比它小，
//         即更新了tails的中间元素，也不会影响当前的maxLen，因为这样得到的maxLen依然是valid的。
// 时间复杂度：O(n log n), n = envelopes.length
// 空间复杂度：O(n), n = envelopes.length
// 犯错点：1.当generic type为int[]时Comparator的写法
//         2.排序时，先对width从小到大排列，对于【width相同的情况，要从大到小排列】
// 拓展：如果要得到长度最大时的envelopes subsequence，那么envelopes[i]只能更新tails[maxLen]，即append新元素到tails数组末尾

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        
        /* sort the envelopes by the width */
        Arrays.sort(envelopes, new Comparator<int[]>() {  // {Mistake 1: generic type should be specific for int array} {Correction 1}
            @Override
            public int compare(int[] e1, int[] e2) {
                return e1[0] == e2[0] ? e2[1] - e1[1] : e1[0] - e2[0];  // {Mistake 2: for the envelopes with the same width, they should be sorted descending by height} {Correction 2}
            }
        });
        
        /* transform to the longest increasing subsequence question */
        int[][] tails = new int[envelopes.length][2];
        tails[0] = envelopes[0];
        int maxLen = 1;
        for (int i = 1; i < envelopes.length; i++) {
            int index = binarySearch(envelopes, i, tails, maxLen);  // apply binary search to find the proper position to update
            tails[index] = envelopes[i];
            if (index == maxLen) maxLen++;  // if envelopes[i] should append to the tail of tails array, update maxLen
        }
        return maxLen;
    }
    
    private int binarySearch(int[][] envelopes, int i, int[][] tails, int maxLen) {
        int low = 0, high = maxLen - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (envelopes[i][1] == tails[mid][1]) return mid;
            else if (envelopes[i][1]  > tails[mid][1]) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }
}