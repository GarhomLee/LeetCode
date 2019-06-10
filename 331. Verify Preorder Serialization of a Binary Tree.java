https://leetcode.com/problems/verify-preorder-serialization-of-a-binary-tree/

// 解法一：Stack
//     由tree的性质可知，null node（即"#"）的个数一定比non-null node的个数多1个，可以把null node当成是leave node。
//     如果遇到"#"，分两种情况：
//     1）Stack已经空了，那么只需要判断"#"是不是最后一个元素
//     2）Stack非空，pop出一个元素
//     如果遇到非"#"，直接push进Stack。
//     如果循环结束还没返回结果，那么一定是Stack还有元素，所以返回false。
// 时间复杂度：O(n)
// 空间复杂度：O(n)

class Solution {
    public boolean isValidSerialization(String preorder) {
        String[] split = preorder.split(",");
        Stack<String> stack = new Stack();
        for (int i = 0; i < split.length; i++) {
            if (split[i].equals("#")) {
                if (stack.isEmpty()) return i == split.length - 1;  // check if it is the last "#" and also the end of split array
                else stack.pop();  // pop out a String from Stack
            } else {
                stack.push(split[i]);  // push non-"#" String into Stack
            }
        }
        return false;
    }
}

// 解法二：利用in degree和out degree的性质