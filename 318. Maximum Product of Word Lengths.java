https://leetcode.com/problems/maximum-product-of-word-lengths/

// Brute Force: HashMap，将每个words[i]对应的字符情况记录下来，然后每次都从头到尾遍历两个字符串的字符情况
// 时间复杂度：O(n^2 * k), n=words.length, k=words[i].length
// 空间复杂度：O(n), n=words.length


// 优化：Bit Manipulation，维护长度为n的marks数组，用32位bit的0和1来记录26个字母是否在words[i]出现过。
//      遍历words数组，对于当前words[i]，从右往左第0位为'a'，第1位为'b'… 将出现的字符对应的位置标记为1。
//      然后遍历words[0:i-1]，对于是否有common letters，可以直接用‘&’操作的结果。如果结果为0，那么就表示没有出现相同字符，
//      更新maxProduct。
// 时间复杂度：O(n*(n+k)), n=words.length, k=words[i].length
// 空间复杂度：O(n), n=words.length

class Solution {
    public int maxProduct(String[] words) {
        /* corner case */
        if (words.length < 2) return 0;
        
        int[] marks = new int[words.length];
        int maxProduct = 0;
        for (int i = 0; i < words.length; i++) {
            /* construct marks of words[i] */
            for (char c: words[i].toCharArray()) {
                marks[i] = marks[i] | (1 << (c - 'a'));
            }
            
            for (int j = 0; j < i; j++) {
                if ((marks[i] & marks[j]) != 0) continue;  // words[i] and words[j] share common letters
                maxProduct = Math.max(maxProduct, words[i].length() * words[j].length());
            }
        }
        
        return maxProduct;
    }
}