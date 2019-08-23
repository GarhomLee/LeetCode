https://leetcode.com/problems/remove-comments/

// 解法一：常规String扫描，扫描每一个字符
//         step0: 维护变量isBlock，记录当前状态，是否处于block comment当中，初始化为false。
//         step1: 遍历每个source字符串数组的元素str。如果isBlock==false，那么StringBuilder sb需要初始化为空；
//             否则，当前处于block comment当中，sb中的字符不能清空，如果有新的有效字符可以append在sb末尾。
//         step2: 遍历str的每个字符，按isBlock是否为true分成两种大的情况。
//             对于isBlock==true，当前字符处在block comment当中，有两种情况：
//             1）找到了end tag "*/"，那么将isBlock更新为false，指针i也更新成i+2。
//             2）其他情况，因为在block comment中，所以只需更新i++
//             对于isBlock==false，当前字符不在block comment中，有三种情况：
//             1）搜索到start tag "/*"，那么将isBlock更新为true，指针i也更新成i+2。
//             2）搜索到line comment "//"，后面的字符不需要再扫描，直接break。
//             3）其他情况，因为在block comment之外，所以要将当前字符加入sb，并更新i++
//         step3: 遍历完str的每个字符，需要判断当前sb是否要加入res数组。只有当!isBlock且sb.length() > 0，才能
//             把当前行搜索到的字符加入res数组。
// 时间复杂度：O(s), s=total number of all characters
// 空间复杂度：O(s), s=total number of all characters

class Solution {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        boolean isBlock = false;
        StringBuilder sb = new StringBuilder();
        for (String str: source) {
            if (!isBlock) {
                sb = new StringBuilder();
            }
            
            int i = 0;
            while (i < str.length()) {
                if (isBlock) {
                    if (i < str.length() - 1 && str.charAt(i) == '*' && str.charAt(i + 1) == '/') {
                        isBlock = false;
                        i += 2;
                    } else {
                        i++;
                    }
                } else {
                    if (i < str.length() - 1 && str.charAt(i) == '/' && str.charAt(i + 1) == '*') {
                        isBlock = true;
                        i += 2;
                    } else if (i < str.length() - 1 && str.charAt(i) == '/' && str.charAt(i + 1) == '/') {
                        break;
                    } else {
                        sb.append(str.charAt(i++));
                    }
                }
            }
            
            if (!isBlock && sb.length() > 0) {
                res.add(sb.toString());
            }
        }
        
        return res;
    }
}


解法二：Regex。参考：https://leetcode.com/problems/remove-comments/discuss/109195/1-liners

public List<String> removeComments(String[] source) {
    String[] s = String.join("\n",source).replaceAll("//.*|/\\*(.|\n)*?\\*/", "").split("\n");
    List<String> ans = new ArrayList<>();
    for(String str : s) if(!str.equals("")) ans.add(str);
    return ans;
}