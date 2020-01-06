https://leetcode.com/problems/shortest-way-to-form-string/

初步解法：Greedy
        对于target中的每个字符，从source的当前字符开始从左往右找对应的字符，利用index % len来处理回到
        最左字符的情况。
时间复杂度：O(s * t), s=source.length(), t=target.length()
空间复杂度：O(26) = O(1)
犯错点：1.细节错误：如果搜索完所有target字符，index % len不为0，说明还需要多用一个source，因此返回的是
            index / len + 1.
各种优化follow-up：https://leetcode.com/problems/shortest-way-to-form-string/discuss/330938/Accept-is-not-enough-to-get-a-hire.-Interviewee-4-follow-up

class Solution {
    public int shortestWay(String source, String target) {
        Set<Character> set = new HashSet<>();
        for (char c : source.toCharArray()) {
            set.add(c);
        }
        
        int index = 0, len = source.length();
        for (char c : target.toCharArray()) {
            if (!set.contains(c)) {
                return -1;
            }
            
            while (source.charAt(index % len) != c) {
                index++;
            }
            index++;
        }
        
        int count = index % len == 0 ? 0 : 1;
        count += index / len;
        return count;
    }
}