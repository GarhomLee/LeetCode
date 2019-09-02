https://leetcode.com/problems/strobogrammatic-number-ii/

// 思路：Backtracking
//         goal：左指针left超过了右指针right，表示没有可以填充的位置了，将结果的左右两部分组合起来加入res。
//         choices："00", "11", "88", "69", "96"，左边的数字填在left位置，右边数字填在right位置。
//         constraints：1）当只剩下一个位置时，只能选前三种，后两种"69"和"96"没法填入一个位置
//                     2）当n>1时，最左边的数字不能为0，所以"00"不能选
// 时间复杂度：O(n)
// 空间复杂度：O(n)
// 犯错点：1.边界条件错误：不能存在leading 0s，但当n==1时可以让单独一个"0"存在。因此，要对leading 0s的情况进行判断，
//             使得大于1位数的第一位一定不是0。

class Solution {
    String[] candidates = new String[]{"00", "11", "88", "69", "96"};  // All possible choices
    List<String> res = new ArrayList<>();
    
    public List<String> findStrobogrammatic(int n) {
        dfs(0, n - 1, "", "");
        return res;
    }
    
    private void dfs(int left, int right, String leftPart, String rightPart) {
        if (left > right) {  // GOAL: no more space to fill
            res.add(leftPart + rightPart);
            return;
        }
        
        for (int i = 0; i < (left == right ? 3 : 5); i++) {  // CONSTRAINTS: when there is only 1 space, "69" or "96" should not be considered
            // {Mistake 1}
            //if (leftPart.length() == 0 && i == 0) continue;  // {Mistake 1}
            if (leftPart.length() == 0 && i == 0 && left != right) continue;  // {Correction 1}  // CONSTRAINTS: avoid leading 0s if n > 1
            
            char c1 = candidates[i].charAt(0), c2 = candidates[i].charAt(1);
            if (left == right) {  // CONSTRAINTS: when there is only 1 space, just add one char
                dfs(left + 1, right - 1, leftPart + c1, rightPart);
            } else {
                dfs(left + 1, right - 1, leftPart + c1, c2 + rightPart);
            }
        }
    }
}