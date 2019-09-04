https://leetcode.com/problems/repeated-dna-sequences/

// 思路：HashSet
//         维护两个HashSet，第一个seen存储出现过的substring，第二个res存储出现过至少两次的substring。
//         最后返回时将res转成ArrayList。
// 时间复杂度：O(len), len = s.length()
// 空间复杂度：O(len), len = s.length()
// 犯错点：1.判断条件错误：要使得最后一个s[i:i+10)是最后一个substring，i最大为i - 10，所以是i < i - 9。

class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> seen = new HashSet<>();
        Set<String> res = new HashSet<>();
        int len = s.length();
        if (len < 10) {
            return new ArrayList<String>();
        }
        
        //for (int i = 0; i < len - 10; i++)  // {Mistake 1}
        for (int i = 0; i < len - 9; i++) {  // {Correction 1}
            String substring = s.substring(i, i + 10);
            if (seen.contains(substring)) {
                res.add(substring);
            }
            seen.add(substring);
        }
        
        return new ArrayList<String>(res);
    }
}