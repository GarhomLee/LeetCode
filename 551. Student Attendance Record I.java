https://leetcode.com/problems/student-attendance-record-i/

// 思路：简单的逻辑判断。
//      'A'总共不能出现超过两次，因此用一个counter来记录'A'出现的次数。
//      'L'不能连续出现两次，因此同样用counter来记录'L'出现次数，但当'P'或'A'出现时次数清零。

class Solution {
    public boolean checkRecord(String s) {
        if (s.length() < 2) return true;
        
        int[] count = new int[2];
        for (char c: s.toCharArray()) {
            switch (c) {
                case 'P': count[1] = 0; break;
                case 'A': {
                    count[0]++;
                    count[1] = 0;
                    if (count[0] > 1) return false;
                    break;
                }
                case 'L': {
                    count[1]++;
                    if (count[1] > 2) return false;
                }   
            }
        }
        return true;
    }
}