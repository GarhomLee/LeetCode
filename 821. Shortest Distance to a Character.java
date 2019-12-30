https://leetcode.com/problems/shortest-distance-to-a-character/

// 解法一：Two-pass (Greedy？)
//         第一遍：从左往右扫描，记录距离左边最近的char C的距离为dist[i]。
//         第二遍：从右往左扫描，根据已经记录的距离左边最近的char C的距离和当前离右边最近的char C的距离
//             的较小值更新dist[i]。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public int[] shortestToChar(String S, char C) {
        int len = S.length();
        int[] dist = new int[len];
        int curr = len;
        for (int i = 0; i < len; i++) {
            curr = S.charAt(i) == C ? 0 : curr + 1;
            dist[i] = curr;
        }
        
        curr = len;
        for (int i = len - 1; i >= 0; i--) {
            curr = S.charAt(i) == C ? 0 : curr + 1;
            dist[i] = Math.min(dist[i], curr);
        }
        
        return dist;
    }
}


// 解法二：BFS

class Solution {
    public int[] shortestToChar(String S, char C) {
        int len = S.length();
        int[] dist = new int[len];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < len; i++) {
            if (S.charAt(i) == C) {
                dist[i] = 0;
                queue.offer(i);
            } else {
                dist[i] = len;
            }
        }
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int curr = queue.poll();
                int left = curr - 1, right = curr + 1;
                if (left >= 0 && dist[left] > step + 1) {
                    dist[left] = step + 1;
                    queue.offer(left);
                }
                if (right < len && dist[right] > step + 1) {
                    dist[right] = step + 1;
                    queue.offer(right);
                }
            }
            
            step++;
        }
        
        return dist;
    }
}