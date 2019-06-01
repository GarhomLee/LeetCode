https://leetcode.com/problems/different-ways-to-add-parentheses/

// 总体思路：Recursion with Memoization
//         以操作符'+','-','*'为界，将String s分为左右两边，递归求解左右两边所有可能的结果，对于每一个左半边的结果，都可以用操作符对右半边的结果进行操作。
//         最后得到所有可能的结果，为当前String s的所有可能结果，存储在Map里，key为当前String s，value为包含所有可能结果的List。
//         当运算到相同的String s时，可以直接调用Map里的结果，减少计算操作。
//         终止条件：1.String s的结果已经计算过，存在了Map里。
//                 2.String s没有任何操作符'+','-','*'，为纯数字。

class Solution {
    Map<String, List<Integer>> map = new HashMap();  // store a certain expression with all possible results with different parentheses

    public List<Integer> diffWaysToCompute(String input) {
        return ways(input);
    }

    /** helper method, just use the same function with a simpler name */
    private List<Integer> ways(String s) {
        if (map.containsKey(s)) return map.get(s);  // use memoization
        
        List<Integer> list = new ArrayList<>();
        if (s.indexOf('+') < 0 && s.indexOf('-') < 0 && s.indexOf('*') < 0 ) {
            list.add(Integer.parseInt(s));
            map.put(s, list);
            return list;    
        }
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != '+' && c != '-' && c != '*') continue;
            
            List<Integer> leftList = ways(s.substring(0, i));
            List<Integer> rightList = ways(s.substring(i + 1));
            for (Integer l: leftList) {
                for (Integer r: rightList) {
                    switch (c) {
                        case '+': list.add(l + r); break;
                        case '-': list.add(l - r); break;
                        case '*': list.add(l * r); break;
                    }
                }
            }
        }
        map.put(s, list);
        return list;
    }
}