https://leetcode.com/problems/longest-happy-string/

idea: PriorityQueue +  Greedy
time complexity:O(sum log sum), sum=a+b+c
space complexity: O(sum)

class Solution {
    class Pair {
        char ch;
        int count;
        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }
    
    public String longestDiverseString(int a, int b, int c) {
        // if (a == 0 && b == 0 && c == 0) {
        //     return "";
        // }
        
        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> y.count - x.count);
        if (a > 0) pq.offer(new Pair('a', a));
        if (b > 0) pq.offer(new Pair('b', b));
        if (c > 0) pq.offer(new Pair('c', c));
        
        StringBuilder sb = new StringBuilder();
        Pair temp = null;
        while (!pq.isEmpty()) {
            Pair curr = pq.poll();
            if (temp != null) {
                pq.offer(temp);
            }
            int decr = Math.min(2, curr.count);
            for (int i = 0; i < decr; i++) {
                sb.append(curr.ch);
            }
            
            curr.count -= decr;
            temp = curr.count == 0 ? null : curr;
        }
        
        if (temp != null) {
            for (int i = sb.length() - 2; i >= 0 && temp.count > 0; i--) {
                char c1 = sb.charAt(i), c2 = sb.charAt(i + 1);
                if (c1 != temp.ch && c2 != temp.ch) {
                    int decr = Math.min(2, temp.count);
                    for (int j = 0; j < decr; j++) {
                        sb.insert(i + 1, temp.ch);
                    }
                    
                    temp.count -= decr;
                }
            }
        }
        
        return sb.toString();
    }
}