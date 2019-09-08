https://leetcode.com/problems/longest-absolute-file-path/

// 思路：String.split() + String.lastIndexOf() + Stack。参考：https://leetcode.com/problems/longest-absolute-file-path/discuss/86615/9-lines-4ms-Java-solution
//         维护Stack，stack[i]存储层数为[0：i)的路径长度（包含'/'在末尾）。如：最外层为"dir/"，层数为1。
//         step1:以"\n"分割，每一个片段代表每一层的directory或者file。
//         step2:对于每一层，可以用lastIndexOf()得到tab的个数。注意，"\t"中带有转义符，所以长度只算为1.
//             得到的tab的个数+1即为层数。因此，层数为0表示空字符串；最外层为"dir/"，tab的个数为0，所以层数为1。
//         step3:从Stack储存的信息中得到当前层level之前的路径长度，也就是要得到stack[level]，从而得到[0：level)
//             的路径长度。因此，当stack.size() > level，就要不断移除顶部元素。
//         step4:当前层的长度为[0：level)的路径长度stack.peek()，加上除了tab之外的字符长度，再加上末尾的'/'。
//             将currLen加入Stack。
//         step5:如果当前片段包含"."，说明是file，那么利用currLen-1（把末尾的'/'减去）更新maxLen。
// 时间复杂度：O(N)
// 空间复杂度：O(N)

class Solution {
    public int lengthLongestPath(String input) {
        if (input == null || input.length() == 0 || input.indexOf(".") < 0) {
            return 0;
        }
        
        int maxLen = 0;
        Stack<Integer> stack = new Stack();
        stack.push(0);  // 
        String[] split = input.split("\n");
        for (String str: split) {
            int numOfTab = str.lastIndexOf("\t") + 1;  // get number of tabs in current str, note that the length of "\t" is counted as 1
            int level = numOfTab + 1;  // get the level of current str, "dir" is level 1 and so forth
            while (stack.size() > level) {  // get the total length of previous directory
                stack.pop();
            }
            
            int currLen = stack.peek() + str.length() - numOfTab + 1;  // add 1 more length for "/" at the end
            stack.push(currLen);
            
            if (str.indexOf(".") >= 0) {  // str contains a file
                maxLen = Math.max(maxLen, currLen - 1);  // get rid of the length for "/" at the end
            }
        }
        
        return maxLen;
    }
}