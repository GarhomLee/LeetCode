https://leetcode.com/problems/find-common-characters/

// 思路：Hash Table，用array模拟
//         step1: 维护total数组，记录每个字符在所有String里出现的最小值。
//         step2: 遍历A数组所有String，维护临时变量count数组。
//                 然后遍历每一个字符，更新count数组。
//                 遍历完当前String，利用count数组更新total数组。
//         step3:遍历完A数组，total数组记录的就是至少在所有String里都出现过的字符，以及出现的次数。
//                 转化成String，加入res列表。
// 时间复杂度：O(A * l), A=A.length, l=average length of each String
// 空间复杂度：O(1)

class Solution {
    public List<String> commonChars(String[] A) {
        int[] total = new int[26];
        Arrays.fill(total, Integer.MAX_VALUE);
        
        for (String s: A) {
            int[] count = new int[26];
            for (char c: s.toCharArray()) {
                count[c - 'a']++;
            }
            
            for (int i = 0; i < 26; i++) {
                total[i] = Math.min(total[i], count[i]);
            }
        }
        
        List<String> res = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            char c = (char) ('a' + i);
            for (int j = 0; j < total[i]; j++) {
                res.add(String.valueOf(c));
            }
        }
        
        return res;
    }
}