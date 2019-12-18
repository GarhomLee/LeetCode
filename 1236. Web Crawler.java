https://leetcode.com/problems/web-crawler/

思路：BFS + String.contains()

时间复杂度：O(n)
空间复杂度：O(n)

class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        List<String> res = new ArrayList<>();
        String[] split = startUrl.split("/");
        String startHost = split[2];
        
        Set<String> set = new HashSet<>();
        set.add(startUrl);
        Queue<String> queue = new LinkedList<>();
        queue.offer(startUrl);
        while (!queue.isEmpty()) {
            String url = queue.poll();
            res.add(url);
            for (String nextUrl : htmlParser.getUrls(url)) {
                if (!nextUrl.contains(startHost) || set.contains(nextUrl)) continue;
                
                set.add(nextUrl);
                queue.offer(nextUrl);
            }
        }
        
        return res;
    }
}