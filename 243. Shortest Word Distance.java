https://leetcode.com/problems/shortest-word-distance/

// 思路：常规扫描 + 记录位置
//         维护变量：index1，记录最近出现的word1的位置；index2，记录最近出现的word2的位置；minDist，当前找到的
//         word1和word2的最小距离。
//         遍历words数组，每次更新index1或index2时，用index1和index2的距离更新minDist。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int minDist = words.length;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            } else if (words[i].equals(word2)) {
                index2 = i;
            }
            
            if (index1 >= 0 && index2 >= 0) {
                minDist = Math.min(minDist, Math.abs(index1 - index2));
            }
        } 
        
        return minDist;
    }
}