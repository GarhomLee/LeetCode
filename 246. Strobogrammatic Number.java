https://leetcode.com/problems/strobogrammatic-number/

// 思路：HashSet
//         维护HashSet，表示合法的左右指针指向的数字的组合。如果数字组合不在HashSet中， 说明不合法，返回false。
//         遍历了整个String，都合法，那么返回true。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    Set<String> set = new HashSet<>(Arrays.asList("00", "11", "88", "69", "96"));
    
    public boolean isStrobogrammatic(String num) {
        int left = 0, right = num.length() - 1;
        while (left <= right) {
            char c1 = num.charAt(left), c2 = num.charAt(right);
            if (!set.contains(c1 + "" + c2)) {  // transform this pair of chars to a String and use HashSet to validate
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}