https://leetcode.com/problems/compare-version-numbers/

// 总体思路：将字符以"."分隔，取其Integer的表示形式，然后比较。

// 关键优化步骤：1）如果超出边界，根据题意，可以赋值为0，便于后续的比较
//              2）将compareTo()的结果存在临时变量，简化代码

class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\."), v2 = version2.split("\\.");
        int i = 0, j = 0;
        while (i < v1.length || j < v2.length) {
            Integer num1 = i < v1.length ? Integer.valueOf(v1[i++]) : 0;
            Integer num2 = j < v2.length ? Integer.valueOf(v2[j++]) : 0;
            int compare = num1.compareTo(num2);
            if (compare != 0) return compare; 
        }
        return 0;
    }
}