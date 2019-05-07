https://leetcode.com/problems/restore-ip-addresses/

// 总体思路：属于Backtracking问题。把String分段，看是否valid，最后是否能达到goal。注意choices：1，2，3个相邻digits；constraints：大于255越界，0在开头只能有1位

class Solution {
    List<String> res = new ArrayList<>();  // global variable

    public List<String> restoreIpAddresses(String s) {
        if (s == null || s.length() == 0) return res;  // corner cases
        find(s, new ArrayList<Integer>(), 0);
        return res;
    }
    private void find(String s, List<Integer> list, int start) {
        /* GOAL: 4 elements in the list and reaches the end of the String */
        if (list.size() >= 4) {
            if (start == s.length()) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < 3; i++) {
                    sb.append(list.get(i)).append(".");
                }
                sb.append(list.get(3));  // there is no "." at the end
                res.add(sb.toString());
            }
            return;
        }
        for (int i = start + 1; i <= Math.min(s.length(), start + 3); i++) {  // CHOICES: there might be 3 digits at most
            int num = Integer.parseInt(s.substring(start, i));  // transform substring into int
            if (num > 255) break;  // CONSTRAINTS: out of range, not a valid ip address
            list.add(num);
            find(s, list, i);  // recursion, backtracking
            list.remove(list.size() - 1);  // reset
            if (num == 0) break;  // CONSTRAINTS:  break the loop if the num from the first substring is 0
        }
    }
}