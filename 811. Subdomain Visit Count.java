https://leetcode.com/problems/subdomain-visit-count/solution/

// 思路：Hash Table
//         HashMap的key为每个subdomain，value为对应的出现次数。
// 时间复杂度：O(n * l), n=cpdomains.length, l=average length of each string
// 空间复杂度：O(n * l), n=cpdomains.length, l=average length of each string

class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (String domain : cpdomains) {
            String[] split = domain.split(" ");
            int count = Integer.parseInt(split[0]);
            // int index = split[1].indexOf(".");
            int start = 0;
            while (start >= 0) {
                String substr = split[1].substring(start);
                map.put(substr, map.getOrDefault(substr, 0) + count);
                int index = split[1].indexOf(".", start);
                start = index >= 0 ? index + 1 : -1;
            }
        }
        
        List<String> res = new ArrayList<>();
        for (String domain : map.keySet()) {
            res.add(map.get(domain) + " " + domain);
        }
        
        return res;
    }
}