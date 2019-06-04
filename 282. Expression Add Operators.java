https://leetcode.com/problems/expression-add-operators/

// 总体思路：DFS，用recursion来实现，可以看作是Backtracking问题。
//         goal:扫描完整个String，且当前计算的数值
//         choices:从start index开始，所有的num[start:i)都可以作为当前处理的数。对于每个num[start:i)，都有'+'，'-'，'*'三种操作
//         constraints:1）处理的数字有leading 0，即0只能单独一位
//                     2）超过Integer.MAX_VALUE，虽然题目没有明说
//                     3）从头开始处理时，下一层的expression为当前substring，而pre，curr为当前substring对应的num
//         利用helper method，维护以下变量：
//             1）nums数组，为原input的String num转换而来
//             2）target，目标值
//             3）expression，和得到当前计算值的相关表达式
//             4）pre，前一个节点的计算值，用来处理'*'的计算。如果要进行'*'操作，那么pre要更新为pre*num，否则，更新为num或-num。
//             5）curr，根据expression得到的当前计算值。如果要进行'*'操作，那么在下一层递归中更新为curr-pre+pre*num，否则，更新为curr+num，curr-num
//             6）start，在nums数组中处理字符的起始位置
// 犯错点：1.当进行'-'操作时，pre要用-num。

class Solution {
    List<String> res = new ArrayList();
    
    public List<String> addOperators(String num, int target) {
        dfs(num.toCharArray(), target, "", 0, 0, 0);
        return res;
    }
    
    private void dfs(char[] nums, int target, String expression, long pre, long curr, int start) {
        /* termination condition */
        if (start == nums.length) {
            if (curr == target) {
                res.add(expression);
            }
            return;
        }
        
        long num = 0;
        for (int i = start + 1; i <= nums.length; i++) {
            if (i > start + 1 && nums[start] == '0') break;  // "0XXXX" is not valid
            
            num = num * 10 + nums[i - 1] - '0';  // optimization: convert char array into long
            if (num > Integer.MAX_VALUE) break;  // too long
            
            String substring = new String(nums, start, i - start);
            if (start == 0) {
                dfs(nums, target, substring, num, num, i);
            } else {
                dfs(nums, target, expression + "+" + substring, num, curr + num, i);
                //dfs(nums, target, expression + "-" + substring, num, curr - num, i);  // {Mistake 1}                
                dfs(nums, target, expression + "-" + substring, -num, curr - num, i);  // {Correcton 1: pre should be -num}
                dfs(nums, target, expression + "*" + substring, pre * num, curr - pre + pre * num, i);
            }
        }
    }
}