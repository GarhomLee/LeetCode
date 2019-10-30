https://leetcode.com/problems/robot-return-to-origin/

// 思路：统计各字符出现次数。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public boolean judgeCircle(String moves) {
        int verticalCount = 0, horizontalCount = 0;
        for (char c : moves.toCharArray()) {
            switch (c) {
                case 'L':
                    horizontalCount--;
                    break;
                case 'R':
                    horizontalCount++;
                    break;
                case 'U':
                    verticalCount--;
                    break;
                case 'D':
                    verticalCount++;
                    break;
            }
        }
        
        return verticalCount == 0 && horizontalCount == 0;
    }
}