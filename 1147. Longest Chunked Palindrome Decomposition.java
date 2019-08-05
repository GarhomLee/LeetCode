https://leetcode.com/problems/longest-chunked-palindrome-decomposition/

// 思路：Greedy + Recursion
//         观察题目可知，只要找到长度最小的palindrome位置作为分割点，就不需要再找更长的palindrome，而是可以
//         递归求解下一个分割点，这样得到的片段数是最大的。（Greedy）
//         递归函数定义：int dfs(String text, int start, int end)，表示求出text[start:end]分割成符合题意
//                 的片段数的最大值。
//         终止条件：start > end，非法范围，返回0
//         递归过程：维护两个指针left和right，分别从start和end向中间移动。记text[start:left]为leftPart，
//                 text[right:end]为rightPart，判断leftPart和rightPart是否为相同字符串。
//                 如果是，那么就可以直接返回2 + dfs(text, left + 1, right - 1)，表示结果为leftPart和rightPart
//                 2个片段再加上调用递归函数求得的text[left+1:right-1]的结果。
//                 如果没有相同的leftPart和rightPart，那么返回1表示整个片段。
// 时间复杂度：O(n)
// 空间复杂度：最大递归深度O(n)

class Solution {
    public int longestDecomposition(String text) {
        return dfs(text, 0, text.length() - 1);
    }
    
    private int dfs(String text, int start, int end) {
        if (start > end) {
            return 0;
        }
        
        int left = start, right = end;
        int res = 1;
        while (left < right) {
            String leftPart = text.substring(start, left + 1);
            String rightPart = text.substring(right, end + 1);
            if (leftPart.equals(rightPart)) {
                return 2 + dfs(text, left + 1, right - 1);
            }
            left++;
            right--;
        }

        return res;
    }
}