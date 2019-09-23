https://leetcode.com/problems/can-make-palindrome-from-substring/

// 思路：Prefix Sum
//         step1:先预先统计处每个s[0:i]中26个字母对应的个数。
//         step2:由于可以rearrange，所以字符出现的顺序是无关紧要的，只需要统计字符出现的频数。
//             对于每个query中的范围s[left:right]，遍历26个字母，根据prefix sum的信息得到出现奇数次的
//             字符的总个数odd，这些奇数次字符可能导致不能形成palindrome。
//         step3:由于有mismatch = query[2]次的更改次数，那么换掉1个奇数次字符，就会有2个字符配对，即实际上
//             消掉了2个奇数次字符。因此，一共可以消掉 mismatch*2 个奇数次字符。只要odd - mismatch * 2 <= 1，
//             这个substring就可以形成palindrome。
// 时间复杂度：O(q), q=queries.length
// 空间复杂度：O(s), s=s.length()
// 犯错点：1.TLE：如果对每个query中的s[left:right]遍历扫描去得到odd，那么时间复杂度为O(q*s)，会导致超时。
//             因此，可以采取预处理求出每个s[0:i]中每个字符的个数，那么求s[left:right]中奇数次字符的个数odd
//             只需要遍历26个字母。
//         2.细节错误：当采取了预处理，奇数次字符的个数odd就只需要对奇数次字符+1，而不需要对偶数次字符-1。

class Solution {
    int[][] count;
    
    public List<Boolean> canMakePaliQueries(String s, int[][] queries) {
        List<Boolean> res = new ArrayList<>();
        count = new int[s.length() + 1][26];
        
        for (int i = 1; i <= s.length(); i++) {
            count[i] = count[i - 1].clone();
            count[i][s.charAt(i - 1) - 'a']++;
        }
        
        for (int[] query: queries) {
            int odd = 0;
            int left = query[0], right = query[1], mismatch = query[2];
            for (int i = 0; i < 26; i++) {
                // odd += (count[i][right + 1] - count[i][left]) % 2 == 1 ? 1 : -1;  // {Mistake 2}
                odd += (count[right + 1][i] - count[left][i]) % 2;  // {Correction 2}
            }
            res.add(odd - mismatch * 2 <= 1);
        }
        
        return res;
    }
}