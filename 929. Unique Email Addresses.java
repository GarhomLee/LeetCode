https://leetcode.com/problems/unique-email-addresses/

// 解法一：HashMap + HashSet
// 时间复杂度：O(n*l), n=emails.length, l=average length of each email
// 空间复杂度：O(n), n=emails.length

class Solution {
    public int numUniqueEmails(String[] emails) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String email: emails) {
            String[] split = email.split("@");
            StringBuilder localname = new StringBuilder();
            for (int i = 0; i < split[0].length(); i++) {
                char c = split[0].charAt(i);
                if (c == '.') continue;
                if (c == '+') break;
                
                localname.append(c);
            }
            map.putIfAbsent(split[1], new HashSet<>());
            map.get(split[1]).add(localname.toString());
        }
        
        int count = 0;
        for (Set<String> set: map.values()) {
            count += set.size();
        }
        
        return count;
    }
}


解法二：HashSet + indexOf() + replace()