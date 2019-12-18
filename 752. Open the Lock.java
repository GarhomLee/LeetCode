https://leetcode.com/problems/open-the-lock/

思路：BFS

时间复杂度：O(4^2 * 10^4 + d), d=deadends.length
空间复杂度：O(10^4 + d), d=deadends.length
犯错点：1.细节错误：将arr[i]+1和arr[i]-1的情况分开写的时候，不能在第一个if不满足时continue，否则即使
        第二个if满足条件也会被跳过。

class Solution {
    public int openLock(String[] deadends, String target) {
        Set<String> banned = new HashSet<>(Arrays.asList(deadends));
        if (banned.contains("0000")) {
            return -1;
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String curr = queue.poll();
                if (curr.equals(target)) {
                    return step;
                }
                
                for (int i = 0; i < 4; i++) {
                    char[] arr = curr.toCharArray();
                    char c1 = arr[i] == '9' ? '0' : (char) (arr[i] + 1);
                    char c2 = arr[i] == '0' ? '9' : (char) (arr[i] - 1);
                    
                    arr[i] = c1;
                    String newStr = new String(arr);
                    if (!banned.contains(newStr) && !seen.contains(newStr)) {
                        seen.add(newStr);
                        queue.offer(newStr);
                    }
                    
                    
                    arr[i] = c2;
                    newStr = new String(arr);
                    if (!banned.contains(newStr) && !seen.contains(newStr)) {
                        seen.add(newStr);
                        queue.offer(newStr);
                    }
                }
            }
            step++;
        }
        
        return -1;
    }
}