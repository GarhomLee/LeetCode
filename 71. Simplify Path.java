https://leetcode.com/problems/simplify-path/

// 总体思路：根据Linux中".."的性质，用Stack比较合适。考虑到最后从Stack取元素变为String的过程，可以使用Double linked list或者Double ended queue。
//         将原String从"/"分割，如果分割后的片段为空、为"."、或者为".."但Deque为空，都跳过什么都不做。
//         当片段为".."且Deque不为空，将Stack或者Deque的后加入元素取出；当片段为其他的正常元素，加入Stack或者Deque尾部。
//         遍历完所有片段，如果从Stack取剩下的元素加入StringBuilder，需要从后往前构建；如果用的是Deque，直接removeFirst()即可。
//         最后需要判断StringBuilder是否为空，因为至少需要返回一个"/"。

class Solution {
    public String simplifyPath(String path) {
        String[] pathArray = path.split("/");
        ArrayDeque<String> ad = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pathArray.length; i++) {
            if (pathArray[i].length() == 0 || pathArray[i].equals(".")) continue;
            else if (pathArray[i].equals("..")) {
                if (ad.isEmpty()) continue;
                ad.removeLast();
            }
            else ad.addLast(pathArray[i]);
        }
        while (!ad.isEmpty()) {
            sb.append("/").append(ad.removeFirst());
        }
        return sb.length() == 0? "/" : sb.toString();
    }
}

// 条件判断简化版：

for (int i = 0; i < pathArray.length; i++) {
    if (pathArray[i].equals("..") && !ad.isEmpty()) ad.removeLast();
    else if (!pathArray[i].equals("") && !pathArray[i].equals(".") && !pathArray[i].equals("..")) ad.addLast(pathArray[i]);
}