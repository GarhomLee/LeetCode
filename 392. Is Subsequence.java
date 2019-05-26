https://leetcode.com/problems/is-subsequence/

// 总体思路：虽然也是两个字符串的问题，但DP解法【不一定更快】。

// 解法一：Two pointers。两个指针分别在s和t扫描，当s[si] == t[ti]时，si右移一位。只有si == s.length()时才为true。
// 时间复杂度：O(m + n)，m = s.length()，n = t.length()。当t很大时，运行速度比DP解法快。
// 空间复杂度：O(1)
// 犯错点：1.当s为空时，s[0]过界，所以需要在corner case中进行判断。

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.length() == 0) return true;  // {Correction 1}
        if (t.length() == 0 && s.length() != 0) return false;
        
        for (int si = 0, ti = 0; ti < t.length(); ti++) {  // {Mistake 1: when si == 0, if s is empty, it would be out of range. Correction 1: add corner case}
            if (s.charAt(si) == t.charAt(ti)) si++;
            if (si == s.length()) return true;
        }
        return false;
    }
}

// 解法二：DP，维护二维boolean的dp数组（1-based）。状态函数dp[i][j]表示s[0:i - 1]是否为t[0:j - 1]的subsequence；
//         状态转移方程：dp[i][j]取决于s[i]和t[j]，如果s[i] == t[j]，有可能成为新的subsequence，dp[i][j] = dp[i - 1][j - 1]；否则，t[j]不影响结果，dp[i][j] = dp[i][j - 1]。
// 时间复杂度：O(m * n)，m = s.length()，n = t.length()。当t很大时，运行速度很慢。
// 空间复杂度：O(m * n)，m = s.length()，n = t.length()

class Solution {
    public boolean isSubsequence(String s, String t) {
        boolean[][] dp = new boolean[s.length() + 1][t.length() + 1];  // dp[i][j] indicates whether s[0:i - 1] is subsequence of t[0:j - 1]
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= t.length(); j++) {
                if (i == 0) dp[i][j] = true;  // when s is empty, s is always a subsequence of t
                else if (j == 0) dp[i][j] = false;  // when s is not empty but t is empty, s would never be a subsequence of t
                else  dp[i][j] = s.charAt(i - 1) == t.charAt(j - 1) ? dp[i - 1][j - 1] : dp[i][j - 1];
            }
        }
        return dp[s.length()][t.length()];
    }
}

// 解法三：Binary Search，针对follow-up中存在大量String s和一个很长的String t的情况。
//         将String t中出现的char和对应的index用Map（或者长度为128的数组）对应起来，因为char可能出现在多个位置，所以用List存储。
//         对于String s中的char，在一个不断缩小的范围搜索第一个可能出现的位置，范围的左边界为前一个char出现的位置，右边界为t.length().
//         对于某个char，在所有出现的index中搜索大于某给定index的第一个可能出现的位置，属于求upper bound，所以可以用Binary Search。
//         low boundary: 指向在list中的位置，i==0
//         high boundary: 指向在list中的位置，i==list.size()
//         g(m): list.get(mid) > index，即大于给定index的最小index
//         返回值：因为low指向的是list中的位置，要求的是对应的储存的value，即list.get(low)。如果low超出边界，说明upper bound不存在，
//             所以返回-1
// 时间复杂度：O(t + s*log(t)), s=s.length(), t=t.length()
// 空间复杂度：O(t), t=t.length()
// 犯错点：1.新建带有generic type的Object数组时，在new后面不能带generic tpye
//         2.binary search的返回值不是low，low指示的是list中元素的位置，应该返回list中的元素，即list.get(low)

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        if (t.isEmpty() || s.length() > t.length()) return false;
        
        //List<Integer>[] mapping = new ArrayList<Integer>[128];  // {Mistake 1: generic array creation error}
        List<Integer>[] mapping = new ArrayList[128];  // {Correction 1: get rif of generic type}
        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            if (mapping[c] == null) mapping[c] = new ArrayList<Integer>();
            mapping[c].add(i);
        }
        
        int pre = -1;  // the index of previous char in String s
        for (char c: s.toCharArray()) {
            if (mapping[c] == null) return false;  // char c does not exist in String t
            
            int curr = upperBound(mapping[c], pre);  // use binary search to find the index of current char c in String t which should be the smallest index greater than pre, which is the upper bound
            //System.out.println("char: " + c + ", curr: " + curr);
            if (curr == -1) return false;  // the curr index found by binary search is unvalid
            pre = curr;  // update pre as curr
        }
        return true;  // all chars in String s are in valid positions in String t
    }
    
    private int upperBound(List<Integer> list, int index) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) > index) high = mid - 1;
            else low = mid + 1;
        }
        //return low == list.size()? -1 : low;  // {Mistake 2}
        return low == list.size()? -1 : list.get(low);  // if the upper bound of target index does not exist in this list, it means no index related to current char is greater than previous char
                                                        // {Correction 2}
    }
}

// 解法四：和解法三思路类似，但利用String内置的indexOf()来找当前char在String t限定范围内的的index

class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s == null || s.length() == 0) return true;
        if (t == null || t.length() == 0) return false;
        int currIndex = -1;
        char[] sArray = s.toCharArray(), tArray = t.toCharArray();
        for (int i = 0; i < sArray.length; i++) {
            currIndex = t.indexOf(sArray[i], currIndex + 1);
            if (currIndex == -1) return false;
        }
        return true;
    }
}