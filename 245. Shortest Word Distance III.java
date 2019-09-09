https://leetcode.com/problems/shortest-word-distance-iii/

// 对比：243. Shortest Word Distance，本题允许word1和word2相同，但它们的位置不能相同。

// 思路：Two poiners，扫描 + 条件判断
//         维护变量：index1，表示最近出现过的word1的位置，初始化为-1；index2，表示最近出现过的word2的位置，
//         初始化为-1；minDist，表示当前找到的最小距离。
//         遍历words数组，可能出现以下情况：
//         1）words[i]==word1，那么需要更新index1.
//             在更新之前，还需要判断word1是否和word2相同，如果是，那么更新index2=index1，即index2记录上一个
//             word1（也就是word2）出现的位置，然后才更新index1=i。
//         2）words[i]!=word1但words[i]==word2，这种情况下word1一定和word2不相同，因此直接更新index2=i即可。
//         3）其他情况，什么都不做。
//         只有当index1和index2都大于等于0，即word1和word2都至少出现过1次，才更新minDist。
//         最后返回minDist。
// 时间复杂度：O(n)
// 空间复杂度：O(1)

class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int index1 = -1, index2 = -1;
        int minDist = Integer.MAX_VALUE;
        
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                if (word1.equals(word2)) {
                    index2 = index1;  // this will be executed only if word1==word2
                }
                index1 = i;
            } else if (words[i].equals(word2)) {  // this will not be executed when word1==word2
                index2 = i;
            }
            
            if (index1 >= 0 && index2 >= 0) {
                minDist = Math.min(minDist, Math.abs(index1 - index2));
            }
        } 
        
        return minDist;
    }
}